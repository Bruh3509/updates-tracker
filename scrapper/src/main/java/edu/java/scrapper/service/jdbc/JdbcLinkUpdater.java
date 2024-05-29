package edu.java.scrapper.service.jdbc;

import edu.java.scrapper.clients.GitHubClient;
import edu.java.scrapper.clients.StackOverflowClient;
import edu.java.scrapper.dao.jdbc.JdbcChatToLinkDao;
import edu.java.scrapper.dao.jdbc.JdbcLinkDao;
import edu.java.scrapper.domain.jdbc.ChatToLinkDto;
import edu.java.scrapper.dto.bot.LinkUpdate;
import edu.java.scrapper.service.interfaces.LinkUpdater;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;

public class JdbcLinkUpdater implements LinkUpdater {

    private final GitHubClient gitHubClient;
    private final StackOverflowClient stackOverflowClient;
    private final JdbcLinkDao jdbcLinkDao;
    private final JdbcChatToLinkDao jdbcChatToLinkDao;

    public JdbcLinkUpdater(
        @Qualifier("github") GitHubClient gitHubClient,
        @Qualifier("stackoverflow") StackOverflowClient stackOverflowClient,
        JdbcLinkDao jdbcLinkDao,
        JdbcChatToLinkDao jdbcChatToLinkDao
    ) {
        this.gitHubClient = gitHubClient;
        this.stackOverflowClient = stackOverflowClient;
        this.jdbcLinkDao = jdbcLinkDao;
        this.jdbcChatToLinkDao = jdbcChatToLinkDao;
    }

    @Override
    public List<LinkUpdate> update() { // TODO if modified rewrite db record
        return jdbcLinkDao.findAll()
            .stream()
            .filter(link -> (System.currentTimeMillis() - link.curTime()) > FIVE_MINUTES)
            .filter(link -> {
                var name = link.name();
                var uri = URI.create(name);
                String[] pathComponents = uri.getPath().split("/");
                jdbcLinkDao.updateCheck(link);
                if (name.startsWith(GITHUB)) {
                    var response = gitHubClient.getRepository(pathComponents[1], pathComponents[2]);
                    var lastUpdate = response.getBody().pushDate();
                    if (lastUpdate.isAfter(link.lastUpdate())) {
                        jdbcLinkDao.updateModification(lastUpdate, link);
                        return true;
                    }
                } else if (name.startsWith(STACK)) {
                    var response
                        = stackOverflowClient.getQuestionById(Integer.parseInt(pathComponents[2]), SITE);
                    var lastUpdate = response.getBody()
                        .items()
                        .getFirst()
                        .lastActDate();
                    if (lastUpdate.isAfter(link.lastUpdate())) {
                        jdbcLinkDao.updateModification(lastUpdate, link);
                        return true;
                    }
                }
                return false;
            })
            .map(link -> new LinkUpdate(link.id(), link.name(),
                jdbcChatToLinkDao.findByLinkId(link.id()).stream().map(ChatToLinkDto::chatId).toList()
            ))
            .toList();
    }
}

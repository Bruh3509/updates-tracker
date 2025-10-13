package edu.java.scrapper.service.jdbc;

import edu.java.scrapper.clients.GitHubClient;
import edu.java.scrapper.clients.StackOverflowClient;
import edu.java.scrapper.dao.jdbc.JdbcChatToLinkDao;
import edu.java.scrapper.dao.jdbc.JdbcLinkDao;
import edu.java.scrapper.domain.jdbc.ChatToLink;
import edu.java.scrapper.dto.bot.LinkUpdate;
import edu.java.scrapper.service.interfaces.LinkUpdater;
import jakarta.transaction.Transactional;
import java.net.URI;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import static edu.java.scrapper.service.interfaces.LinkUpdater.SITE.GITHUB;
import static edu.java.scrapper.service.interfaces.LinkUpdater.SITE.STACK;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class JdbcLinkUpdater implements LinkUpdater {
    GitHubClient gitHubClient;
    StackOverflowClient stackOverflowClient;
    JdbcLinkDao jdbcLinkDao;
    JdbcChatToLinkDao jdbcChatToLinkDao;

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
                if (name.startsWith(GITHUB.name())) {
                    var response = gitHubClient.getRepository(pathComponents[1], pathComponents[2]);
                    var lastUpdate = response.getBody().pushDate();
                    if (lastUpdate.isAfter(link.lastUpdate())) {
                        jdbcLinkDao.updateModification(lastUpdate, link);
                        return true;
                    }
                } else if (name.startsWith(STACK.name())) {
                    var response
                        = stackOverflowClient.getQuestionById(Integer.parseInt(pathComponents[2]), PARSE.SITE.name());
                    var lastUpdate = response.getBody()
                        .itemDtos()
                        .getFirst()
                        .lastActDate();
                    if (lastUpdate.isAfter(link.lastUpdate())) {
                        jdbcLinkDao.updateModification(lastUpdate, link);
                        return true;
                    }
                }
                return false;
            })
            .map(link -> new LinkUpdate(
                link.id(), link.name(),
                jdbcChatToLinkDao.findByLinkId(link.id()).stream().map(ChatToLink::chatId).toList()
            ))
            .toList();
    }
}

package edu.java.scrapper.service.jdbc;

import edu.java.scrapper.clients.GitHubClient;
import edu.java.scrapper.clients.StackOverflowClient;
import edu.java.scrapper.dao.jdbc.JdbcLinkDao;
import edu.java.scrapper.dto.jdbc.LinkDto;
import edu.java.scrapper.service.interfaces.LinkUpdater;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class JdbcLinkUpdater implements LinkUpdater {
    private static final int FIVE_MINUTES = 300_000;
    private static final String GITHUB = "https://github.com";
    private static final String STACK = "https://stackoverflow.com";
    private static final String SITE = "stackoverflow";

    GitHubClient gitHubClient;
    StackOverflowClient stackOverflowClient;
    JdbcLinkDao jdbcLinkDao;

    @Autowired
    public JdbcLinkUpdater(
        @Qualifier("github") GitHubClient gitHubClient,
        @Qualifier("stackoverflow") StackOverflowClient stackOverflowClient,
        JdbcLinkDao jdbcLinkDao
    ) {
        this.gitHubClient = gitHubClient;
        this.stackOverflowClient = stackOverflowClient;
        this.jdbcLinkDao = jdbcLinkDao;
    }

    @Override
    public List<LinkDto> update() { // TODO if modified rewrite db record
        return jdbcLinkDao.findAll()
            .stream()
            .filter(link -> (System.currentTimeMillis() - link.curTime()) > FIVE_MINUTES) // how long ago was checked
            .filter(link -> { // if there are updates + modify table in db if they are
                // TODO maybe the last one should be done in the other place?
                var name = link.name();
                var uri = URI.create(name);
                String[] pathComponents = uri.getPath().split("/");
                jdbcLinkDao.updateCheck(link);
                if (name.startsWith(GITHUB)) {
                    var response = gitHubClient.getRepository(pathComponents[1], pathComponents[2]);
                    if (response.getBody()
                        .pushDate()
                        .isAfter(link.lastUpdate())) {
                        jdbcLinkDao.updateModification(link);
                        return true;
                    }
                } else if (name.startsWith(STACK)) {
                    var response
                        = stackOverflowClient.getQuestionById(Integer.parseInt(pathComponents[2]), SITE);
                    if (response.getBody()
                        .items()
                        .getFirst()
                        .lastActDate()
                        .isAfter(link.lastUpdate())) {
                        jdbcLinkDao.updateModification(link);
                        return true;
                    }
                }
                return false;
            })
            .toList(); // now we have links which was updated and return them
    }
}

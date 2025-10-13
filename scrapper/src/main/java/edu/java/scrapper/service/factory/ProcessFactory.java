package edu.java.scrapper.service.factory;

import edu.java.scrapper.clients.GitHubClient;
import edu.java.scrapper.clients.StackOverflowClient;
import edu.java.scrapper.dao.hibernate.HibernateLinkDao;
import edu.java.scrapper.service.interfaces.LinkUpdater;
import edu.java.scrapper.service.processors.GitHubProcessor;
import edu.java.scrapper.service.processors.Processor;
import edu.java.scrapper.service.processors.StackOverflowProcessor;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProcessFactory {

    GitHubClient gitHubClient;
    StackOverflowClient stackOverflowClient;
    HibernateLinkDao linkDao;

    public Processor createProcessor(String name) {
        var site = name.startsWith()
        return switch (site) {
            case GITHUB -> new GitHubProcessor(gitHubClient, linkDao);
            case STACK -> new StackOverflowProcessor(stackOverflowClient, linkDao);
        };
    }
}

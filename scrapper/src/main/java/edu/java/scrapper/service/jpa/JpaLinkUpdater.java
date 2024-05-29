package edu.java.scrapper.service.jpa;

import edu.java.scrapper.clients.GitHubClient;
import edu.java.scrapper.clients.StackOverflowClient;
import edu.java.scrapper.dto.bot.LinkUpdate;
import edu.java.scrapper.entity.Chat;
import edu.java.scrapper.dao.jpa.LinkRepository;
import edu.java.scrapper.service.interfaces.LinkUpdater;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class JpaLinkUpdater implements LinkUpdater {
    private final GitHubClient gitHubClient;
    private final StackOverflowClient stackOverflowClient;
    private final LinkRepository linkRepository;

    @Autowired
    public JpaLinkUpdater(
        @Qualifier("github") GitHubClient gitHubClient,
        @Qualifier("stackoverflow") StackOverflowClient stackOverflowClient,
        LinkRepository linkRepository
    ) {
        this.gitHubClient = gitHubClient;
        this.stackOverflowClient = stackOverflowClient;
        this.linkRepository = linkRepository;
    }

    @Override
    public List<LinkUpdate> update() {
        return linkRepository.findAll()
            .stream()
            .filter(link -> (System.currentTimeMillis() - link.getCurTime()) > FIVE_MINUTES)
            .filter(link -> {
                var name = link.getName();
                var uri = URI.create(name);
                String[] pathComponents = uri.getPath().split("/");
                linkRepository.updateCheck(System.currentTimeMillis(), link.getId());
                if (name.startsWith(GITHUB)) {
                    var response = gitHubClient.getRepository(pathComponents[1], pathComponents[2]);
                    var pushDate = response.getBody().pushDate();
                    if (pushDate.isAfter(link.getLastUpdate())) {
                        linkRepository.updateModification(pushDate, link.getId());
                        return true;
                    }
                } else if (name.startsWith(STACK)) {
                    var response
                        = stackOverflowClient.getQuestionById(Integer.parseInt(pathComponents[2]), SITE);
                    var lastUpdate = response.getBody()
                        .items()
                        .getFirst()
                        .lastActDate();
                    if (lastUpdate.isAfter(link.getLastUpdate())) {
                        linkRepository.updateModification(lastUpdate, link.getId());
                        return true;
                    }
                }
                return false;
            })
            .map(link -> new LinkUpdate(
                link.getId(),
                link.getName(),
                link.getFollowingChats().stream().map(Chat::getChatId).toList() // List of chat's id
            ))
            .toList();
    }
}

package edu.java.scrapper.service.jpa;

import edu.java.scrapper.clients.GitHubClient;
import edu.java.scrapper.clients.StackOverflowClient;
import edu.java.scrapper.dao.jpa.LinkRepository;
import edu.java.scrapper.dto.bot.LinkUpdate;
import edu.java.scrapper.entity.Chat;
import edu.java.scrapper.service.interfaces.LinkUpdater;
import java.net.URI;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.transaction.annotation.Transactional;
import static edu.java.scrapper.service.interfaces.LinkUpdater.SITE.GITHUB;
import static edu.java.scrapper.service.interfaces.LinkUpdater.SITE.STACK;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class JpaLinkUpdater implements LinkUpdater {
    GitHubClient gitHubClient;
    StackOverflowClient stackOverflowClient;
    LinkRepository linkRepository;

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
                if (name.startsWith(GITHUB.name())) {
                    var response = gitHubClient.getRepository(pathComponents[1], pathComponents[2]);
                    var pushDate = response.getBody().pushDate();
                    if (pushDate.isAfter(link.getLastUpdate())) {
                        linkRepository.updateModification(pushDate, link.getId());
                        return true;
                    }
                } else if (name.startsWith(STACK.name())) {
                    var response
                        = stackOverflowClient.getQuestionById(Integer.parseInt(pathComponents[2]), PARSE.SITE.name());
                    var lastUpdate = response.getBody()
                        .itemDtos()
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

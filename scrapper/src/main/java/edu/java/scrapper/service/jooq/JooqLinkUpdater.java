package edu.java.scrapper.service.jooq;

import edu.java.scrapper.clients.GitHubClient;
import edu.java.scrapper.clients.StackOverflowClient;
import edu.java.scrapper.dao.jooq.JooqChatToLinkDao;
import edu.java.scrapper.dao.jooq.JooqLinkDao;
import edu.java.scrapper.dto.bot.LinkUpdate;
import edu.java.scrapper.service.interfaces.LinkUpdater;
import java.net.URI;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.transaction.annotation.Transactional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class JooqLinkUpdater implements LinkUpdater {
    GitHubClient gitHubClient;
    StackOverflowClient stackOverflowClient;
    JooqLinkDao linkDao;
    JooqChatToLinkDao chatToLinkDao;

    @Override
    public List<LinkUpdate> update() {
        return linkDao.findAll()
            .stream()
            .filter(link -> (System.currentTimeMillis() - link.curTime()) > FIVE_MINUTES)
            .filter(link -> {
                var name = link.name();
                var uri = URI.create(name);
                String[] pathComponents = uri.getPath().split("/");
                linkDao.updateCheck(link);
                if (name.startsWith(GITHUB)) {
                    var response = gitHubClient.getRepository(pathComponents[1], pathComponents[2]);
                    var lastUpdate = response.getBody().pushDate();
                    if (lastUpdate.isAfter(link.lastUpdate())) {
                        linkDao.updateModification(lastUpdate, link);
                        return true;
                    }
                } else if (name.startsWith(STACK)) {
                    var response
                        = stackOverflowClient.getQuestionById(Integer.parseInt(pathComponents[2]), SITE);
                    var lastUpdate = response.getBody()
                        .itemDtos()
                        .getFirst()
                        .lastActDate();
                    if (lastUpdate.isAfter(link.lastUpdate())) {
                        linkDao.updateModification(lastUpdate, link);
                        return true;
                    }
                }

                return false;
            })
            .map(link -> new LinkUpdate(link.id(), link.name(), chatToLinkDao.findByLinkId(link.id())))
            .toList();
    }
}

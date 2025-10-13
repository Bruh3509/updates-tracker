package edu.java.scrapper.service.processors;

import edu.java.scrapper.clients.StackOverflowClient;
import edu.java.scrapper.dao.hibernate.HibernateLinkDao;
import edu.java.scrapper.entity.Link;
import edu.java.scrapper.service.interfaces.LinkUpdater;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StackOverflowProcessor implements Processor {

    StackOverflowClient stackOverflowClient;
    HibernateLinkDao linkDao;

    @Override
    public boolean process(String[] pathComponents, Link link) {
        var response
            = stackOverflowClient.getQuestionById(Integer.parseInt(pathComponents[2]), LinkUpdater.PARSE.SITE.name());
        var lastUpdate = response.getBody()
            .itemDtos()
            .getFirst()
            .lastActDate();
        if (lastUpdate.isAfter(link.getLastUpdate())) {
            linkDao.updateModification(lastUpdate, link.getId());
            return true;
        }

        return false;
    }
}

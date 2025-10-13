package edu.java.scrapper.service.processors;

import edu.java.scrapper.clients.GitHubClient;
import edu.java.scrapper.dao.hibernate.HibernateLinkDao;
import edu.java.scrapper.entity.Link;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class GitHubProcessor implements Processor {

    GitHubClient gitHubClient;
    HibernateLinkDao linkDao;

    @Override
    public boolean process(String[] pathComponents, Link link) {
        var response = gitHubClient.getRepository(pathComponents[1], pathComponents[2]);
        var pushDate = response.getBody().pushDate();
        if (pushDate.isAfter(link.getLastUpdate())) {
            linkDao.updateModification(pushDate, link.getId());
            return true;
        }

        return false;
    }
}

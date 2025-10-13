package edu.java.scrapper.service.hibernate;

import edu.java.scrapper.dao.hibernate.HibernateLinkDao;
import edu.java.scrapper.dto.bot.LinkUpdate;
import edu.java.scrapper.service.factory.ProcessFactory;
import edu.java.scrapper.service.interfaces.LinkUpdater;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.net.URI;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class HibernateLinkUpdater implements LinkUpdater {

    HibernateLinkDao linkDao;
    ProcessFactory processFactory;

    @Override
    public List<LinkUpdate> update() {
        return linkDao.findAll()
            .stream()
            .filter(link -> (System.currentTimeMillis() - link.getCurTime()) > FIVE_MINUTES)
            .filter(link -> {
                var name = link.getName();
                var uri = URI.create(name);
                String[] pathComponents = uri.getPath().split("/");
                linkDao.updateCheck(System.currentTimeMillis(), link.getId());

                processFactory.createProcessor(name);
                return false;
            });
    }
}

package edu.java.scrapper.shedule;

import edu.java.scrapper.kafka.service.interfaces.SendUpdatesService;
import edu.java.scrapper.service.interfaces.LinkUpdater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableScheduling
public class LinkUpdaterScheduler {
    public static final String NEW_UPDATE = "New update!";
    private final LinkUpdater linkUpdater;
    private final SendUpdatesService updatesService;

    @Autowired
    public LinkUpdaterScheduler(
        LinkUpdater linkUpdater,
        SendUpdatesService updatesService
    ) {
        this.linkUpdater = linkUpdater;
        this.updatesService = updatesService;
    }

    @Scheduled(fixedRateString = "${app.scheduler.interval}")
    public void update() {
        log.info("Update call!");
        var updates = linkUpdater.update();
        updatesService.sendUpdates(updates);
    }
}

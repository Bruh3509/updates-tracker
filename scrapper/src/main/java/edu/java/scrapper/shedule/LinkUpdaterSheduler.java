package edu.java.scrapper.shedule;

import edu.java.scrapper.clients.BotClient;
import edu.java.scrapper.dto.bot.PostRequest;
import edu.java.scrapper.service.interfaces.LinkUpdater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableScheduling
public class LinkUpdaterSheduler {
    public static final String NEW_UPDATE = "New update!";
    private final LinkUpdater linkUpdater;
    private final BotClient botClient;

    @Autowired
    public LinkUpdaterSheduler(
        LinkUpdater linkUpdater,
        BotClient botClient
    ) {
        this.linkUpdater = linkUpdater;
        this.botClient = botClient;
    }

    @Scheduled(fixedRateString = "${app.scheduler.interval}")
    public void update() {
        log.info("Update call!");
        var updates = linkUpdater.update();
        updates
            .forEach(update -> botClient.sendUpdates(new PostRequest(
                update.id(),
                update.name(),
                NEW_UPDATE,
                update.chatId()
            )));
    }
}

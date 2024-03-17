package edu.java.scrapper.shedule;

import edu.java.scrapper.clients.BotClient;
import edu.java.scrapper.dao.jdbc.JdbcChatToLinkDao;
import edu.java.scrapper.dto.bot.PostRequest;
import edu.java.scrapper.dto.jdbc.ChatToLinkDto;
import edu.java.scrapper.service.interfaces.LinkUpdater;
import edu.java.scrapper.service.jdbc.JdbcLinkUpdater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableScheduling
public class LinkUpdaterSheduler {
    private final LinkUpdater linkUpdater;
    private final BotClient botClient;
    private final JdbcChatToLinkDao jdbcChatToLinkDao;

    @Autowired
    public LinkUpdaterSheduler(
        JdbcLinkUpdater linkUpdater,
        BotClient botClient,
        JdbcChatToLinkDao jdbcChatToLinkDao
    ) {
        this.linkUpdater = linkUpdater;
        this.botClient = botClient;
        this.jdbcChatToLinkDao = jdbcChatToLinkDao;
    }

    @Scheduled(fixedRateString = "${app.scheduler.interval}")
    public void update() {
        log.info("Update call!");
        var updates = linkUpdater.update();
        updates
            .forEach(update -> botClient.sendUpdates(new PostRequest(
                update.id(),
                update.name(),
                "TODO description of the update",
                jdbcChatToLinkDao
                    .findByLinkId(update.id())
                    .stream()
                    .map(ChatToLinkDto::chatId)
                    .toList()
            )));
    }
}

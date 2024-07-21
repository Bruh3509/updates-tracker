package edu.java.scrapper.update.service.send;

import edu.java.scrapper.clients.BotClient;
import edu.java.scrapper.dto.bot.LinkUpdate;
import edu.java.scrapper.dto.bot.PostRequest;
import edu.java.scrapper.update.service.interfaces.SendUpdatesService;
import java.util.List;
import static edu.java.scrapper.update.shedule.LinkUpdaterScheduler.NEW_UPDATE;

public class HttpService implements SendUpdatesService {
    private final BotClient botClient;

    public HttpService(BotClient botClient) {
        this.botClient = botClient;
    }

    @Override
    public void sendUpdates(List<LinkUpdate> updates) {
        updates
            .forEach(update -> botClient.sendUpdates(new PostRequest(
                update.id(),
                update.name(),
                NEW_UPDATE,
                update.chatId()
            )));
    }
}

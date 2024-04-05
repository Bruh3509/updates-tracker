package edu.java.scrapper.kafka.service.interfaces;

import edu.java.scrapper.dto.bot.LinkUpdate;
import java.util.List;

public interface SendUpdatesService {
    void sendUpdates(List<LinkUpdate> updates);
}

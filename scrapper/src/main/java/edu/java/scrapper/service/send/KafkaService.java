package edu.java.scrapper.service.send;

import edu.java.scrapper.dto.bot.LinkUpdate;
import edu.java.scrapper.kafka.ScrapperQueueProducer;
import edu.java.scrapper.service.interfaces.SendUpdatesService;
import java.util.List;

public class KafkaService implements SendUpdatesService {
    private final ScrapperQueueProducer scrapperQueueProducer;

    public KafkaService(ScrapperQueueProducer scrapperQueueProducer) {
        this.scrapperQueueProducer = scrapperQueueProducer;
    }

    @Override
    public void sendUpdates(List<LinkUpdate> updates) {
        updates.forEach(scrapperQueueProducer::send);
    }
}

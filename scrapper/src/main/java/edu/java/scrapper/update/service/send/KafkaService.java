package edu.java.scrapper.update.service.send;

import edu.java.scrapper.dto.bot.LinkUpdate;
import edu.java.scrapper.kafka.producers.ScrapperQueueProducer;
import edu.java.scrapper.update.service.interfaces.SendUpdatesService;
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

package edu.java.scrapper.kafka.service.send;

import edu.java.scrapper.dto.bot.LinkUpdate;
import edu.java.scrapper.kafka.producers.ScrapperQueueProducer;
import edu.java.scrapper.kafka.service.interfaces.SendUpdatesService;
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

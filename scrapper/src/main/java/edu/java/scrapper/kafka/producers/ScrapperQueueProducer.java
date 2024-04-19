package edu.java.scrapper.kafka.producers;

import edu.java.scrapper.dto.bot.LinkUpdate;
import edu.java.scrapper.dto.bot.LinkUpdateJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ScrapperQueueProducer {
    private final KafkaTemplate<String, LinkUpdateJson> kafkaTemplate;

    @Autowired
    public ScrapperQueueProducer(KafkaTemplate<String, LinkUpdateJson> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(LinkUpdate update) {
        kafkaTemplate.send(
            "updates.topic",
            new LinkUpdateJson(update.id(), update.name(), update.chatId())
        );
    }
}

package edu.java.scrapper.kafka;

import edu.java.scrapper.dto.bot.LinkUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ScrapperQueueProducer {
    private final KafkaTemplate<String, LinkUpdate> kafkaTemplate;

    @Autowired
    public ScrapperQueueProducer(KafkaTemplate<String, LinkUpdate> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(LinkUpdate update) {
        kafkaTemplate.send("updates.topic", update);
    }
}

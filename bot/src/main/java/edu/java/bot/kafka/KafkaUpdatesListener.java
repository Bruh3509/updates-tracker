package edu.java.bot.kafka;

import edu.java.bot.bot.BotSendUpdate;
import edu.java.bot.dto.bot.SendUpdateDto;
import edu.java.scrapper.dto.bot.LinkUpdateJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaUpdatesListener {
    private final BotSendUpdate sendUpdate;

    @Autowired
    public KafkaUpdatesListener(BotSendUpdate sendUpdate) {
        this.sendUpdate = sendUpdate;
    }

    @RetryableTopic(attempts = "1", kafkaTemplate = "dlqKafkaTemplate")
    @KafkaListener(topics = "updates.topic",
                   groupId = "updates-tracker-user",
                   containerFactory = "kafkaListenerContainerFactory")
    public void handleMessage(LinkUpdateJson update) {
        try {
            log.info(update.name());
            sendUpdate.sendUpdate(new SendUpdateDto(update.chatId(), update.name()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

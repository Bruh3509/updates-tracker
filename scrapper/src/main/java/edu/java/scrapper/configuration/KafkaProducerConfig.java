package edu.java.scrapper.configuration;

import edu.java.scrapper.clients.BotClient;
import edu.java.scrapper.dto.bot.LinkUpdate;
import edu.java.scrapper.kafka.ScrapperQueueProducer;
import edu.java.scrapper.service.interfaces.SendUpdatesService;
import edu.java.scrapper.service.send.HttpService;
import edu.java.scrapper.service.send.KafkaService;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@ComponentScan
public class KafkaProducerConfig {
    @Bean
    public ProducerFactory<String, LinkUpdate> producerFactory(ApplicationConfig config) {
        var kafka = config.kafka();
        return new DefaultKafkaProducerFactory<>(Map.ofEntries(
            Map.entry(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.bootstrapServers()),
            Map.entry(ProducerConfig.CLIENT_ID_CONFIG, kafka.clientId()),
            Map.entry(ProducerConfig.ACKS_CONFIG, kafka.acksMode()),
            Map.entry(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, (int) kafka.deliveryTimeout().toMillis()),
            Map.entry(ProducerConfig.LINGER_MS_CONFIG, kafka.lingerMs()),
            Map.entry(ProducerConfig.BATCH_SIZE_CONFIG, kafka.batchSize()),
            Map.entry(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, kafka.maxInFlightPerConnection()),
            Map.entry(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, kafka.enableIdempotence()),
            Map.entry(JsonSerializer.ADD_TYPE_INFO_HEADERS, false),
            Map.entry(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class),
            Map.entry(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class)
        ));
    }

    @Bean
    public KafkaTemplate<String, LinkUpdate> kafkaTemplate(ProducerFactory<String, LinkUpdate> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public SendUpdatesService sendUpdatesService(
        ApplicationConfig config,
        ScrapperQueueProducer queueProducer,
        BotClient botClient
    ) {
        if (config.useQueue()) {
            return new KafkaService(queueProducer);
        }

        return new HttpService(botClient);
    }
}

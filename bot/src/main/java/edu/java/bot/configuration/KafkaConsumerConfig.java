package edu.java.bot.configuration;

import edu.java.scrapper.dto.bot.LinkUpdateJson;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ConsumerFactory<String, LinkUpdateJson> consumerFactory(ApplicationConfig config) {
        var kafka = config.kafka();
        return new DefaultKafkaConsumerFactory<>(Map.ofEntries(
            Map.entry(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.bootstrapServers()),
            Map.entry(ConsumerConfig.GROUP_ID_CONFIG, kafka.groupId()),
            Map.entry(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafka.autoOffsetReset()),
            Map.entry(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, kafka.maxPollIntervalMs()),
            Map.entry(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kafka.enableAutoCommit()),
            Map.entry(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class),
            Map.entry(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class),
            Map.entry(JsonDeserializer.TRUSTED_PACKAGES,  "*")
        ));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, LinkUpdateJson> kafkaListenerContainerFactory(
        ConsumerFactory<String, LinkUpdateJson> consumerFactory,
        ApplicationConfig config
    ) {
        ConcurrentKafkaListenerContainerFactory<String, LinkUpdateJson> factory
            = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(config.kafka().concurrency());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }
}

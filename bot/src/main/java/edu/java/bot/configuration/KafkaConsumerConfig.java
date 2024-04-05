package edu.java.bot.configuration;

import edu.java.bot.dto.kafka.LinkUpdateJson;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import java.util.Map;

public class KafkaConsumerConfig {
    @Bean
    public ConsumerFactory<String, LinkUpdateJson> consumerFactory(ApplicationConfig config) {
        var kafka = config.kafka();
        return new DefaultKafkaConsumerFactory<>(Map.ofEntries(
            Map.entry(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.bootstrapServers()),
            Map.entry(ConsumerConfig.CLIENT_ID_CONFIG, kafka.clientId()),
            Map.entry(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class),
            Map.entry(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class)
        ));
    }

    @Bean
    public KafkaMessageListenerContainer<String, LinkUpdateJson> kafkaListenerContainerFactory(
        ConsumerFactory<String, LinkUpdateJson> consumerFactory
    ) {
        return new KafkaMessageListenerContainer<>(consumerFactory); // TODO
    }
}

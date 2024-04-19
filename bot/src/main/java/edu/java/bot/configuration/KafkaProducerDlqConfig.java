package edu.java.bot.configuration;

import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerDlqConfig {
    @Bean
    public ProducerFactory<String, String> dlqProducerFactory(ApplicationConfig config) {
        var kafka = config.kafka();
        return new DefaultKafkaProducerFactory<>(Map.ofEntries(
            Map.entry(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.bootstrapServers()),
            Map.entry(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class),
            Map.entry(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
        ));
    }

    @Bean
    public KafkaTemplate<String, String> dlqKafkaTemplate(
        ProducerFactory<String, String> dlqProducerFactory
    ) {
        return new KafkaTemplate<>(dlqProducerFactory);
    }
}

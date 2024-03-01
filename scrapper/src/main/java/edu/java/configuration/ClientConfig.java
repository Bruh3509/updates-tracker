package edu.java.configuration;

import edu.java.clients.BotClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfig {
    public static final String BASE_BOT_URL = "";

    @Bean
    BotClient botClient() {
        RestClient client = RestClient
            .builder()
            .baseUrl(BASE_BOT_URL)
            .build();

        HttpServiceProxyFactory factory
            = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client)).build();

        return factory.createClient(BotClient.class);
    }
}

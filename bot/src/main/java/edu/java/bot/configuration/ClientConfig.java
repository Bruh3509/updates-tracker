package edu.java.bot.configuration;

import edu.java.bot.clients.ScrapperClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfig {
    public static final String BASE_URL = "http://localhost:8080";

    @Bean
    @Scope("prototype")
    ScrapperClient scrapperClient() {
        RestClient client = RestClient
            .builder()
            .baseUrl(BASE_URL)
            .build();

        HttpServiceProxyFactory factory
            = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client)).build();

        return factory.createClient(ScrapperClient.class);
    }
}

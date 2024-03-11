package edu.java.scrapper.configuration;

import edu.java.scrapper.clients.BotClient;
import edu.java.scrapper.clients.GitHubClient;
import edu.java.scrapper.clients.StackOverflowClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfig {
    public static final String BASE_BOT_URL = "";
    private static final String LOCAL_HOST_8080 = "http://localhost:8080";
    private static final String BASE_GITHUB_URL = "https://api.github.com";
    private static final String BASE_STACKOVERFLOW_URL = "https://api.stackexchange.com";

    @Bean("github")
    GitHubClient gitHubClient() {
        RestClient restClient = RestClient
            .builder()
            .baseUrl(BASE_GITHUB_URL)
            .build();

        HttpServiceProxyFactory factory
            = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();

        return factory.createClient(GitHubClient.class);
    }

    @Bean("githubLocalhost")
    GitHubClient gitHubClientLocalhost() {
        RestClient restClient = RestClient
            .builder()
            .baseUrl(LOCAL_HOST_8080)
            .build();

        HttpServiceProxyFactory factory
            = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();

        return factory.createClient(GitHubClient.class);
    }

    @Bean("stackoverflow")
    StackOverflowClient stackOverflowClient() {
        RestClient restClient = RestClient
            .builder()
            .baseUrl(BASE_STACKOVERFLOW_URL)
            .build();

        HttpServiceProxyFactory factory
            = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();

        return factory.createClient(StackOverflowClient.class);
    }

    @Bean("stackoverflowLocalhost")
    StackOverflowClient stackOverflowClientLocalhost() {
        RestClient restClient = RestClient
            .builder()
            .baseUrl(LOCAL_HOST_8080)
            .build();

        HttpServiceProxyFactory factory
            = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();

        return factory.createClient(StackOverflowClient.class);
    }
  
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

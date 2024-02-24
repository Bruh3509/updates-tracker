package edu.java.scrapper.configuration;

import edu.java.scrapper.clients.StackOverflowClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfig {
    private static final String BASE_GITHUB_URL = "";
    private static final String BASE_STACKOVERFLOW_URL = "http://stackoverflow.com";

    /*
    @Bean
    GitHubClient gitHubClient(String baseUrl) {
        RestClient restClient = RestClient
            .builder()
            .baseUrl(baseUrl)
            .build();

        HttpServiceProxyFactory factory
            = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();

        return factory.createClient(GitHubClient.class);
    }

    @Bean
    GitHubClient gitHubClient() {
        RestClient restClient = RestClient
            .builder()
            .baseUrl(BASE_GITHUB_URL)
            .build();

        HttpServiceProxyFactory factory
            = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();

        return factory.createClient(GitHubClient.class);
    }
     */
    /*
    @Bean
    StackOverflowClient stackOverflowClientWithUrl(String baseUrl) {
        RestClient restClient = RestClient
            .builder()
            .baseUrl(baseUrl)
            .build();

        HttpServiceProxyFactory factory
            = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();

        return factory.createClient(StackOverflowClient.class);
    }
     */
    @Bean
    StackOverflowClient stackOverflowClient() {
        RestClient restClient = RestClient
            .builder()
            .baseUrl(BASE_STACKOVERFLOW_URL)
            .build();

        HttpServiceProxyFactory factory
            = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();

        return factory.createClient(StackOverflowClient.class);
    }
}

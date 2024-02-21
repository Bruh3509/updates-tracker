package edu.java.configuration;

import edu.java.clients.GitHubClient;
import edu.java.clients.StackOverflowClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

public class ClientConfig {
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
            .baseUrl("")
            .build();

        HttpServiceProxyFactory factory
            = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();

        return factory.createClient(GitHubClient.class);
    }

    @Bean
    StackOverflowClient stackOverflowClient(String baseUrl) {
        RestClient restClient = RestClient
            .builder()
            .baseUrl(baseUrl)
            .build();

        HttpServiceProxyFactory factory
            = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();

        return factory.createClient(StackOverflowClient.class);
    }

    @Bean
    StackOverflowClient stackOverflowClient() {
        RestClient restClient = RestClient
            .builder()
            .baseUrl("")
            .build();

        HttpServiceProxyFactory factory
            = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();

        return factory.createClient(StackOverflowClient.class);
    }
}

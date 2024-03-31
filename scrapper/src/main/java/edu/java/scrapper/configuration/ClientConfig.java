package edu.java.scrapper.configuration;

import edu.java.scrapper.clients.BotClient;
import edu.java.scrapper.clients.GitHubClient;
import edu.java.scrapper.clients.StackOverflowClient;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@EnableCaching
public class ClientConfig {
    public static final String BASE_BOT_URL = "http://localhost:8090";
    private static final String LOCAL_HOST_8080 = "http://localhost:8080";
    private static final String BASE_GITHUB_URL = "https://api.github.com";
    private static final String BASE_STACKOVERFLOW_URL = "https://api.stackexchange.com";
    private static final String HEADER = "X-Forwarded-For";

    @Bean("github")
    GitHubClient gitHubClient() {
        RestClient restClient = RestClient
            .builder()
            .baseUrl(BASE_GITHUB_URL)
            .defaultHeader(HEADER)
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
            .defaultHeader(HEADER)
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
            .defaultHeader(HEADER)
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
            .defaultHeader(HEADER)
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
            .defaultHeader(HEADER)
            .build();

        HttpServiceProxyFactory factory
            = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client)).build();

        return factory.createClient(BotClient.class);
    }

    @Bean
    FilterRegistrationBean<Filter> xForwardedForFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new Filter() {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
                if (request instanceof HttpServletRequest httpServletRequest) {
                    String xForwardedForHeader = httpServletRequest.getHeader(HEADER);
                    if (xForwardedForHeader == null || xForwardedForHeader.isEmpty()) {
                        String clientIpAddress = httpServletRequest.getRemoteAddr();
                        ((HttpServletResponse) response).addHeader(HEADER, clientIpAddress);
                    }
                }
                chain.doFilter(request, response);
            }
        });

        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}

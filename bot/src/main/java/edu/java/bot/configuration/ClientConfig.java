package edu.java.bot.configuration;

import edu.java.bot.clients.ScrapperClient;
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
public class ClientConfig {
    public static final String BASE_URL = "http://localhost:8080";
    private static final String HEADER = "X-Forwarded-For";

    @Bean
    ScrapperClient scrapperClient() {
        RestClient client = RestClient
            .builder()
            .baseUrl(BASE_URL)
            .defaultHeader(HEADER)
            .build();

        HttpServiceProxyFactory factory
            = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client)).build();

        return factory.createClient(ScrapperClient.class);
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

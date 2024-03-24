package edu.java.scrapper.configuration;

import edu.java.scrapper.clients.GitHubClient;
import edu.java.scrapper.clients.StackOverflowClient;
import edu.java.scrapper.repository.ChatRepository;
import edu.java.scrapper.repository.LinkRepository;
import edu.java.scrapper.service.interfaces.ChatService;
import edu.java.scrapper.service.interfaces.LinkService;
import edu.java.scrapper.service.interfaces.LinkUpdater;
import edu.java.scrapper.service.jpa.JpaChatService;
import edu.java.scrapper.service.jpa.JpaLinkService;
import edu.java.scrapper.service.jpa.JpaLinkUpdater;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
@EnableTransactionManagement
@ComponentScan
@SuppressWarnings({"MultipleStringLiterals"})
public class JpaConfig {
    @Value("${spring.datasource.url:jdbc:postgresql://localhost:5432/scrapper}")
    private String jdbcUrl;

    private DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        return dataSource;
    }

    @Bean
    LinkService linkService(
        LinkRepository linkRepository,
        ChatRepository chatRepository
    ) {
        return new JpaLinkService(linkRepository, chatRepository);
    }

    @Bean
    ChatService chatService(ChatRepository chatRepository) {
        return new JpaChatService(chatRepository);
    }

    @Bean
    LinkUpdater linkUpdater(
        @Qualifier("github") GitHubClient gitHubClient,
        @Qualifier("stackoverflow") StackOverflowClient stackOverflowClient,
        LinkRepository linkRepository
    ) {
        return new JpaLinkUpdater(gitHubClient, stackOverflowClient, linkRepository);
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}

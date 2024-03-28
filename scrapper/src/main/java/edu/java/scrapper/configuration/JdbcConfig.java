package edu.java.scrapper.configuration;

import edu.java.scrapper.clients.GitHubClient;
import edu.java.scrapper.clients.StackOverflowClient;
import edu.java.scrapper.dao.jdbc.JdbcChatDao;
import edu.java.scrapper.dao.jdbc.JdbcChatToLinkDao;
import edu.java.scrapper.dao.jdbc.JdbcLinkDao;
import edu.java.scrapper.service.interfaces.ChatService;
import edu.java.scrapper.service.interfaces.LinkService;
import edu.java.scrapper.service.interfaces.LinkUpdater;
import edu.java.scrapper.service.jdbc.JdbcChatService;
import edu.java.scrapper.service.jdbc.JdbcLinkService;
import edu.java.scrapper.service.jdbc.JdbcLinkUpdater;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
@Configuration
@EnableTransactionManagement
@ComponentScan
@SuppressWarnings({"MultipleStringLiterals"})
public class JdbcConfig {
    @Value("#{systemProperties['spring.datasource.url'] ?: 'jdbc:postgresql://localhost:5432/scrapper'}")
    private String jdbcUrl;
    @Value("#{systemProperties['spring.datasource.username'] ?: 'postgres'}")
    private String username;
    @Value("#{systemProperties['spring.datasource.password'] ?: 'postgres'}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public LinkService linkService(
        JdbcLinkDao jdbcLinkDao,
        JdbcChatDao jdbcChatDao,
        JdbcChatToLinkDao jdbcChatToLinkDao
    ) {
        return new JdbcLinkService(jdbcChatToLinkDao, jdbcLinkDao, jdbcChatDao);
    }

    @Bean
    public ChatService chatService(
        JdbcChatDao jdbcChatDao
    ) {
        return new JdbcChatService(jdbcChatDao);
    }

    @Bean
    public LinkUpdater linkUpdater(
        @Qualifier("github") GitHubClient gitHubClient,
        @Qualifier("stackoverflow") StackOverflowClient stackOverflowClient,
        JdbcLinkDao jdbcLinkDao
    ) {
        return new JdbcLinkUpdater(gitHubClient, stackOverflowClient, jdbcLinkDao);
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}

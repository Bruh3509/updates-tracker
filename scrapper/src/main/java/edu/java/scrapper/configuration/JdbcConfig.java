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
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
@Configuration
@EnableTransactionManagement
@ComponentScan
@SuppressWarnings({"MultipleStringLiterals"})
public class JdbcConfig {
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        log.info(jdbcUrl);
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
    public JdbcLinkDao jdbcLinkDao(JdbcTemplate jdbcTemplate) {
        return new JdbcLinkDao(jdbcTemplate);
    }

    @Bean
    public JdbcChatDao jdbcChatDao(JdbcTemplate jdbcTemplate) {
        return new JdbcChatDao(jdbcTemplate);
    }

    @Bean
    public JdbcChatToLinkDao jdbcChatToLinkDao(JdbcTemplate jdbcTemplate) {
        return new JdbcChatToLinkDao(jdbcTemplate);
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
        JdbcLinkDao jdbcLinkDao,
        JdbcChatToLinkDao jdbcChatToLinkDao
    ) {
        return new JdbcLinkUpdater(gitHubClient, stackOverflowClient, jdbcLinkDao, jdbcChatToLinkDao);
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}

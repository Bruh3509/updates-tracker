package edu.java.scrapper.configuration;

import edu.java.scrapper.clients.GitHubClient;
import edu.java.scrapper.clients.StackOverflowClient;
import edu.java.scrapper.dao.hibernate.HibernateChatDao;
import edu.java.scrapper.dao.hibernate.HibernateLinkDao;
import edu.java.scrapper.service.factory.ProcessFactory;
import edu.java.scrapper.service.hibernate.HibernateChatService;
import edu.java.scrapper.service.hibernate.HibernateLinkService;
import edu.java.scrapper.service.hibernate.HibernateLinkUpdater;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class HibernateConfig {
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, Properties hibernateProperties) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("edu.java.scrapper.entity");
        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");

        return hibernateProperties;
    }

    @Bean
    public HibernateLinkDao linkDao(LocalSessionFactoryBean sessionFactory) {
        return new HibernateLinkDao(sessionFactory.getObject());
    }

    @Bean
    public HibernateChatDao chatDao(LocalSessionFactoryBean sessionFactory) {
        return new HibernateChatDao(sessionFactory.getObject());
    }

    @Bean
    public HibernateChatService chatService(HibernateChatDao chatDao) {
        return new HibernateChatService(chatDao);
    }

    @Bean
    public HibernateLinkService linkService(HibernateLinkDao linkDao, HibernateChatDao chatDao) {
        return new HibernateLinkService(linkDao, chatDao);
    }

    @Bean
    public HibernateLinkUpdater hibernateLinkUpdater(HibernateLinkDao linkDao, ProcessFactory processFactory) {
        return new HibernateLinkUpdater(linkDao, processFactory);
    }

    @Bean
    public ProcessFactory processFactory(
        GitHubClient github,
        StackOverflowClient stackoverflow,
        HibernateLinkDao linkDao
    ) {
        return new ProcessFactory(github, stackoverflow, linkDao);
    }
}

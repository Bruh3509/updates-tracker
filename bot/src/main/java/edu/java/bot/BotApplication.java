package edu.java.bot;

import edu.java.bot.configuration.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
@EnableRetry
public class BotApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BotApplication.class, args);
    }
}

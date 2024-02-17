package edu.java.bot;

import edu.java.bot.bot.Bot;
import edu.java.bot.configuration.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BotApplication.class, args);
        try (Bot bot = new Bot()) {
            bot.startBot();
        }
    }
}

package edu.java.bot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SetMyCommands;
import edu.java.bot.commands.Help;
import edu.java.bot.commands.List;
import edu.java.bot.commands.Start;
import edu.java.bot.commands.Track;
import edu.java.bot.commands.Untrack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("edu.java.bot.commands")
@Slf4j
public class BotConfig {
    @Value("${app.telegram-token}")
    private String token;
    @Autowired
    private Start start;
    @Autowired
    private Track track;
    @Autowired
    private Untrack untrack;
    @Autowired
    private List list;
    @Autowired
    private Help help;

    @Bean
    public TelegramBot bot() {
        TelegramBot bot = new TelegramBot(token);

        BotCommand startCommand = new BotCommand(start.command(), start.description());
        BotCommand trackCommand = new BotCommand(track.command(), track.description());
        BotCommand untrackCommand = new BotCommand(untrack.command(), untrack.description());
        BotCommand listCommand = new BotCommand(list.command(), list.description());
        BotCommand helpCommand = new BotCommand(help.command(), help.description());

        SetMyCommands myCommands
            = new SetMyCommands(startCommand, trackCommand, untrackCommand, listCommand, helpCommand);
        bot.execute(myCommands);
        return bot;
    }
}

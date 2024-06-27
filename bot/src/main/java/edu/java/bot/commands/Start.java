package edu.java.bot.commands;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.apiwrapper.UpdateWrapper;
import edu.java.bot.clients.ScrapperClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

@Component
public class Start implements Command {
    private final ScrapperClient scrapperClient;

    @Autowired
    public Start(ScrapperClient scrapperClient) {
        this.scrapperClient = scrapperClient;
    }

    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String description() {
        return "Starts the bot";
    }

    @Override
    public SendMessage handle(UpdateWrapper update) {
        try {
            scrapperClient.regChat(update.chatId());
        } catch (HttpStatusCodeException e) {
            return new SendMessage(update.chatId(), TOO_MANY_REQUESTS);
        }
        return new SendMessage(update.chatId(), String.format(
            "Hello, %s!",
            update.userFName()
        ))
            .parseMode(ParseMode.Markdown);
    }
}

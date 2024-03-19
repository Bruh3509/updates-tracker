package edu.java.bot.commands;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.apiwrapper.UpdateWrapper;
import edu.java.bot.clients.ScrapperClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class Unknown implements Command {
    private final static String UNKNOWN_RESPONSE = """
        `Unknown command =(`
        """;

    @Override
    public String command() {
        return null;
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public SendMessage handle(UpdateWrapper update) {
        return new SendMessage(update.chatId(), UNKNOWN_RESPONSE)
            .parseMode(ParseMode.Markdown);
    }
}

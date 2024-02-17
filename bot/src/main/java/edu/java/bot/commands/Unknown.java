package edu.java.bot.commands;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.apiwrapper.UpdateWrapper;

public final class Unknown implements Command {
    public final static String UNKNOWN_RESPONSE = """
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

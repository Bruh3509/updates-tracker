package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

public final class Untrack implements Command {
    private static final String UNTRACK_RESPONSE = """
        Enter `link` to stop follow!
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
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(), UNTRACK_RESPONSE)
            .parseMode(ParseMode.Markdown);
    }
}

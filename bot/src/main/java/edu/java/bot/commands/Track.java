package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

public final class Track implements Command {
    private static final String TRACK_RESPONSE = """
        Enter `link` to follow!
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
        return new SendMessage(update.message().chat().id(), TRACK_RESPONSE)
            .parseMode(ParseMode.Markdown);
    }
}

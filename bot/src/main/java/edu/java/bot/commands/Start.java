package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

public final class Start implements Command {
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
        // there will be logic for registration
        return new SendMessage(update.message().chat().id(), String.format("Hello, %s!",
                update.message().chat().firstName()))
            .parseMode(ParseMode.Markdown);
    }
}

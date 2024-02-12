package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

public final class Help implements Command {
    private static final String HELP_RESPONSE = """
        `/start` --> starts the bot
        `/help` --> list of commands
        `/track` --> follow new resource
        `/untrack` --> stop following resource\s
        `/list` --> list of following resources""";

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
        return new SendMessage(update.message().chat().id(), HELP_RESPONSE)
            .parseMode(ParseMode.Markdown);
    }
}

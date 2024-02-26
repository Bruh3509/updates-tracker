package edu.java.bot.commands;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.apiwrapper.UpdateWrapper;

public final class Help implements Command {
    private static final String HELP_RESPONSE = """
        `/start` --> starts the bot
        `/help` --> list of commands
        `/track` --> follow new resource
        `/untrack` --> stop following resource\s
        `/list` --> list of following resources""";

    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String description() {
        return "List of commands";
    }

    @Override
    public SendMessage handle(UpdateWrapper update) {
        return new SendMessage(update.chatId(), HELP_RESPONSE)
            .parseMode(ParseMode.Markdown);
    }
}

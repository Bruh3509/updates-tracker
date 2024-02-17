package edu.java.bot.commands;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.apiwrapper.UpdateWrapper;

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
    public SendMessage handle(UpdateWrapper update) {
        // there will be logic for registration
        return new SendMessage(update.chatId(), String.format(
            "Hello, %s!",
            update.userFName()
        ))
            .parseMode(ParseMode.Markdown);
    }
}

package edu.java.bot.commands;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.apiwrapper.UpdateWrapper;
import org.springframework.stereotype.Component;

@Component
public class UntrackInfo implements Command {
    private static final String UNTRACK_RESPONSE = "Enter `link` to stop follow";

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
        return new SendMessage(update.chatId(), UNTRACK_RESPONSE)
            .parseMode(ParseMode.Markdown);
    }
}

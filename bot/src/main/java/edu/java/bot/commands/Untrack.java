package edu.java.bot.commands;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.apiwrapper.UpdateWrapper;
import edu.java.bot.bot.UpdatesProcessor;

public final class Untrack implements Command {
    private static final String UNTRACK_RESPONSE = """
        Enter `link` to stop follow!
        """;
    private static final String UNTRACK_SUCCESS = """
        Successfully untracked!
        """;
    private static final String UNTRACK_NO_PRESENT = """
        No following this link!
        """;
    private boolean isFirstCall = true;

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
        if (!isFirstCall) {
            boolean isPresent = UpdatesProcessor.getFOLLOWING_LINKS().remove(update.messageText());
            isFirstCall = true;
            if (isPresent) {
                return new SendMessage(update.chatId(), UNTRACK_SUCCESS)
                    .parseMode(ParseMode.Markdown);
            }
            return new SendMessage(update.chatId(), UNTRACK_NO_PRESENT);
        }
        isFirstCall = false;
        return new SendMessage(update.chatId(), UNTRACK_RESPONSE)
            .parseMode(ParseMode.Markdown);
    }
}

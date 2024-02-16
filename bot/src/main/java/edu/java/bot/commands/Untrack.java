package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
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
    private static boolean isFirstCall = true;

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
        var message = update.message();
        if (!isFirstCall) {
            boolean isPresent = UpdatesProcessor.getFOLLOWING_LINKS().remove(message.text());
            isFirstCall = true;
            if (isPresent) {
                return new SendMessage(message.chat().id(), UNTRACK_SUCCESS)
                    .parseMode(ParseMode.Markdown);
            }
            return new SendMessage(message.chat().id(), UNTRACK_NO_PRESENT);
        }
        isFirstCall = false;
        return new SendMessage(message.chat().id(), UNTRACK_RESPONSE)
            .parseMode(ParseMode.Markdown);
    }
}

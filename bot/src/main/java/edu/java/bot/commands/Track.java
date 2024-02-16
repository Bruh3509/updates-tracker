package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.bot.UpdatesProcessor;

public final class Track implements Command {
    private static final String TRACK_RESPONSE = """
        Enter `link` to follow!
        """;
    private static final String TRACK_PRESENT = """
        Link is already tracked!
        """;
    private static final String TRACK_SUCCESS = """
        Link is tracked!
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
            var links = UpdatesProcessor.getFOLLOWING_LINKS();
            boolean isPresent = links.contains(message.text());
            isFirstCall = true;
            if (!isPresent) {
                links.add(message.text());
                return new SendMessage(message.chat().id(), TRACK_SUCCESS)
                    .parseMode(ParseMode.Markdown);
            }
            return new SendMessage(message.chat().id(), TRACK_PRESENT);
        }
        isFirstCall = false;
        return new SendMessage(message.chat().id(), TRACK_RESPONSE)
            .parseMode(ParseMode.Markdown);
    }
}

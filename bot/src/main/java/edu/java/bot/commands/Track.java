package edu.java.bot.commands;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.apiwrapper.UpdateWrapper;
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
            var links = UpdatesProcessor.getFOLLOWING_LINKS();
            boolean isPresent = links.contains(update.messageText());
            isFirstCall = true;
            if (!isPresent) {
                links.add(update.messageText());
                return new SendMessage(update.chatId(), TRACK_SUCCESS)
                    .parseMode(ParseMode.Markdown);
            }
            return new SendMessage(update.chatId(), TRACK_PRESENT);
        }
        isFirstCall = false;
        return new SendMessage(update.chatId(), TRACK_RESPONSE)
            .parseMode(ParseMode.Markdown);
    }
}

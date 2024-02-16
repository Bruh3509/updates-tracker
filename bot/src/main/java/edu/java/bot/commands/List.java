package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.bot.UpdatesProcessor;

public final class List implements Command {
    private String getLinks() {
        StringBuilder links = new StringBuilder();
        for (var link : UpdatesProcessor.getFOLLOWING_LINKS()) {
            String cur = "`" + link + "`\n";
            links.append(cur);
        }

        if (links.isEmpty()) {
            links.append("You aren't following something!");
        }

        return links.toString();
    }
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
        return new SendMessage(update.message().chat().id(), getLinks())
            .parseMode(ParseMode.Markdown);
    }
}

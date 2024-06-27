package edu.java.bot.commands;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.apiwrapper.UpdateWrapper;
import edu.java.bot.clients.ScrapperClient;
import edu.java.bot.dto.scrapper.Link;
import edu.java.bot.dto.scrapper.PostRequest;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

@Component
public class Untrack implements Command {
    private static final String UNTRACK_SUCCESS = """
        Successfully untracked!
        """;
    private static final String UNTRACK_NO_PRESENT = """
        No following this link!
        """;
    private final ScrapperClient scrapperClient;

    @Autowired
    public Untrack(ScrapperClient scrapperClient) {
        this.scrapperClient = scrapperClient;
    }

    @Override
    public String command() {
        return "/untrack";
    }

    @Override
    public String description() {
        return "Stop tracking the link";
    }

    @Override
    public SendMessage handle(UpdateWrapper update) {
        List<Link> links;
        try {
            links = scrapperClient.getAllLinks(update.chatId()).getBody().links();
        } catch (HttpStatusCodeException e) {
            return new SendMessage(update.chatId(), TOO_MANY_REQUESTS);
        }
        var userInput = update.messageText();
        boolean isPresent = false;
        for (var link : links) {
            if (link.url().toString().equals(userInput)) {
                isPresent = true;
                break;
            }
        }
        if (isPresent) {
            scrapperClient.deleteLink(
                update.chatId(),
                new PostRequest(new Link(
                    (long) userInput.hashCode(),
                    URI.create(userInput)
                ))
            );
            return new SendMessage(update.chatId(), UNTRACK_SUCCESS)
                .parseMode(ParseMode.Markdown);
        }
        return new SendMessage(update.chatId(), UNTRACK_NO_PRESENT);
    }
}

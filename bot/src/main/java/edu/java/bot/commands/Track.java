package edu.java.bot.commands;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.apiwrapper.UpdateWrapper;
import edu.java.bot.bot.UpdatesProcessor;
import edu.java.bot.clients.ScrapperClient;
import edu.java.bot.dto.scrapper.Link;
import edu.java.bot.dto.scrapper.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.net.URI;

@Component
public class Track implements Command {
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

    private final ScrapperClient scrapperClient;

    @Autowired
    public Track(ScrapperClient scrapperClient) {
        this.scrapperClient = scrapperClient;
    }

    @Override
    public String command() {
        return "/track";
    }

    @Override
    public String description() {
        return "Tracks the link";
    }

    @Override
    public SendMessage handle(UpdateWrapper update) {
        if (!isFirstCall) {
            var userInput = update.messageText();
            var links = scrapperClient.getAllLinks(update.chatId()).getBody().links();
            boolean isPresent = false;
            for (var link : links) {
                if (link.url().toString().equals(userInput)) {
                    isPresent = true;
                    break;
                }
            }
            isFirstCall = true;
            if (!isPresent) {
                scrapperClient.addLink(
                    update.chatId(),
                    new PostRequest(new Link(
                        userInput.hashCode(),
                        URI.create(userInput)
                    ))
                );
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

package edu.java.bot.commands;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.apiwrapper.UpdateWrapper;
import edu.java.bot.clients.ScrapperClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("")
public class List implements Command {
    ScrapperClient scrapperClient;

    @Autowired
    public List(ScrapperClient scrapperClient) {
        this.scrapperClient = scrapperClient;
    }

    private String getLinks(Long id) {
        var response = scrapperClient.getAllLinks(id);
        var links = response.getBody().links();
        StringBuilder result = new StringBuilder();
        links.forEach(link -> result.append("`%s`\n".formatted(link.url().toString())));

        return result.toString();
    }

    public String command() {
        return "/list";
    }

    @Override
    public String description() {
        return "List of tracking links";
    }

    @Override
    public SendMessage handle(UpdateWrapper update) {
        return new SendMessage(update.chatId(), getLinks(update.chatId()))
            .parseMode(ParseMode.Markdown);
    }
}

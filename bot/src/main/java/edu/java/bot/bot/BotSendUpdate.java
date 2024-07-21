package edu.java.bot.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.dto.bot.SendUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BotSendUpdate {
    private final TelegramBot bot;

    @Autowired
    public BotSendUpdate(TelegramBot bot) {
        this.bot = bot;
    }

    public void sendUpdate(SendUpdateDto update) {
        update
            .chatId()
            .forEach(chatId -> bot.execute(new SendMessage(
                chatId,
                String.format("New update: `%s`!", update.name())
            ).parseMode(ParseMode.Markdown)));
    }
}

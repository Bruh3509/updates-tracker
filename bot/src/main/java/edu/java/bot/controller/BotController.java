package edu.java.bot.controller;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.dto.bot.PostRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@Slf4j
public class BotController {
    private final TelegramBot bot;

    @Autowired
    public BotController(TelegramBot bot) {
        this.bot = bot;
    }

    @PostMapping(value = "/updates", consumes = "application/json")
    public ResponseEntity<Void> sendUpdate(@RequestBody PostRequest updateRequest) {
        updateRequest
            .tgChatIds()
            .forEach(chatId -> {
                bot.execute(new SendMessage(
                    chatId,
                    String.format("New update: `%s`!", updateRequest.url())
                ).parseMode(ParseMode.Markdown));
            });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

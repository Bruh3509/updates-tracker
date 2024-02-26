package edu.java.bot.apiwrapper;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

public record UpdateWrapper(Update update) {

    public Long chatId() {
        return update.message().chat().id();
    }

    public String messageText() {
        return update.message().text();
    }

    public String userFName() {
        return update.message().chat().firstName();
    }

    public Message message() {
        return update.message();
    }
}

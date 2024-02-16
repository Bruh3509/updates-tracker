package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public sealed interface Command permits Start, Help, Track, Untrack, List, Unknown {
    public enum Name {
        START,
        HELP,
        TRACK,
        UNTRACK,
        LIST,
        UNKNOWN
    }
    String command(); // TODO
    String description(); // TODO
    SendMessage handle(Update update);
}

package edu.java.bot.commands;

import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.apiwrapper.UpdateWrapper;

// TODO Maybe not final version
public sealed interface Command permits Start, Help, Track, Untrack, List, Unknown {
    enum Name {
        START,
        HELP,
        TRACK,
        UNTRACK,
        LIST,
        UNKNOWN
    }

    String command();

    String description();

    SendMessage handle(UpdateWrapper update);
}

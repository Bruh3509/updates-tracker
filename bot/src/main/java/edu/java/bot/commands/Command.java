package edu.java.bot.commands;

import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.apiwrapper.UpdateWrapper;

public sealed interface Command permits Start, Help, Track, Untrack, List, Unknown {
    enum Name {
        START,
        HELP,
        TRACK,
        UNTRACK,
        LIST,
        UNKNOWN
    }

    String command(); // TODO

    String description(); // TODO

    SendMessage handle(UpdateWrapper update);
}

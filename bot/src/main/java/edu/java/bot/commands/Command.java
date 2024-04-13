package edu.java.bot.commands;

import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.apiwrapper.UpdateWrapper;

public interface Command {
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

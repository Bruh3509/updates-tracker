package edu.java.bot.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import edu.java.bot.commands.Command;
import edu.java.bot.commands.Help;
import edu.java.bot.commands.List;
import edu.java.bot.commands.Start;
import edu.java.bot.commands.Track;
import edu.java.bot.commands.Unknown;
import edu.java.bot.commands.Untrack;
import java.util.ArrayList;
import lombok.Getter;

public class UpdatesProcessor {

    @Getter private static final java.util.List<String> FOLLOWING_LINKS = new ArrayList<>();
    // временное хранилище отслеживаемых ссылок
    // пока не подключится БД
    private static Command.Name prevCommand = Command.Name.START;
    private static boolean isTrackOrUntrack = false;
    private static Command prevCommandObj;

    public static void process(Update update, TelegramBot bot) {
        if (!isTrackOrUntrack) {
            Command command = identifyCommand(update);
            bot.execute(command.handle(update));
        } else {
            bot.execute(prevCommandObj.handle(update));
            isTrackOrUntrack = false;
        }
    }

    private static Command identifyCommand(Update update) {
        return switch (update.message().text()) {
            case "/start" -> {
                //prevCommand = Command.Name.START;
                yield new Start();
            }
            case "/help" -> {
                //prevCommand = Command.Name.HELP;
                yield new Help();
            }
            case "/track" -> {
                //prevCommand = Command.Name.TRACK;
                prevCommandObj =  new Track();
                isTrackOrUntrack = true;
                yield prevCommandObj;
            }
            case "/untrack" -> {
                //prevCommand = Command.Name.UNTRACK;
                prevCommandObj = new Untrack();
                isTrackOrUntrack = true;
                yield prevCommandObj;
            }
            case "/list" -> {
                //prevCommand = Command.Name.LIST;
                yield new List();
            }
            default -> new Unknown();
        };
    }

    // TODO перенес методы в соответсвующие классы (не уверен)
    /*
    private static void track(String link) {
        FOLLOWING_LINKS.add(link);
    }

    private static void untrack(String link) {
        FOLLOWING_LINKS.remove(link);
    }
     */
}

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
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

public class UpdatesProcessor {

    @Getter private static final Set<String> FOLLOWING_LINKS = new HashSet<>(); // временное хранилище отслеживаемых ссылок
    // пока не подключится БД
    private static Command.Name prevCommand = Command.Name.START;

    public static void process(Update update, TelegramBot bot) {
        switch (prevCommand) {
            case Command.Name.TRACK:
                track(update.message().text());
                prevCommand = Command.Name.START;
                break;
            case Command.Name.UNTRACK:
                untrack(update.message().text());
                prevCommand = Command.Name.START;
                break;
            default:
                Command command = switch (update.message().text()) {
                    case "/start" -> {
                        prevCommand = Command.Name.START;
                        yield new Start();
                    }
                    case "/help" -> {
                        prevCommand = Command.Name.HELP;
                        yield new Help();
                    }
                    case "/track" -> {
                        prevCommand = Command.Name.TRACK;
                        yield new Track();
                    }
                    case "/untrack" -> {
                        prevCommand = Command.Name.UNTRACK;
                        yield new Untrack();
                    }
                    case "/list" -> {
                        prevCommand = Command.Name.LIST;
                        yield new List();
                    }
                    default -> new Unknown();
                };
                bot.execute(command.handle(update));
                break;
        }
    }

    private static void track(String link) {
        FOLLOWING_LINKS.add(link);
    }

    private static void untrack(String link) {
        FOLLOWING_LINKS.remove(link);
    }
}

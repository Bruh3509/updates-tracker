package edu.java.bot.bot;

import com.pengrad.telegrambot.TelegramBot;
import edu.java.bot.apiwrapper.UpdateWrapper;
import edu.java.bot.commands.Command;
import edu.java.bot.commands.Help;
import edu.java.bot.commands.List;
import edu.java.bot.commands.Start;
import edu.java.bot.commands.Track;
import edu.java.bot.commands.Unknown;
import edu.java.bot.commands.Untrack;
import java.util.ArrayList;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdatesProcessor {

    @Getter private static final java.util.List<String> FOLLOWING_LINKS = new ArrayList<>();
    // временное хранилище отслеживаемых ссылок
    // пока не подключится БД
    //private static Command.Name prevCommand = Command.Name.START;
    private static boolean isTrackOrUntrack = false;
    private static Command prevCommandObj;

    public static void process(UpdateWrapper update, TelegramBot bot) {
        if (update.message() == null) {
            log.info("No message update");
            return;
        }
        if (!isTrackOrUntrack) {
            Command command = identifyCommand(update);
            bot.execute(command.handle(update));
        } else {
            bot.execute(prevCommandObj.handle(update));
            isTrackOrUntrack = false;
        }
    }

    private static Command identifyCommand(UpdateWrapper update) {
        return switch (update.messageText()) {
            case "/start" -> {
                yield new Start();
            }
            case "/help" -> {
                yield new Help();
            }
            case "/track" -> {
                prevCommandObj = new Track();
                isTrackOrUntrack = true;
                yield prevCommandObj;
            }
            case "/untrack" -> {
                prevCommandObj = new Untrack();
                isTrackOrUntrack = true;
                yield prevCommandObj;
            }
            case "/list" -> {
                yield new List();
            }
            default -> new Unknown();
        };
    }

    private UpdatesProcessor() {
    }
}

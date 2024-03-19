package edu.java.bot.bot;
// TODO DI of commands and track, untrack logic (maybe make additioanl class to just print in first call).
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import edu.java.bot.apiwrapper.UpdateWrapper;
import edu.java.bot.commands.Command;
import edu.java.bot.commands.Help;
import edu.java.bot.commands.List;
import edu.java.bot.commands.Start;
import edu.java.bot.commands.Track;
import edu.java.bot.commands.Unknown;
import edu.java.bot.commands.Untrack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdatesProcessor {
    static class UserState {
        boolean isTrackOrUntrack;
        Command prevCommandObj;

        public UserState(boolean isTrackOrUntrack, Command prevCommandObj) {
            this.isTrackOrUntrack = isTrackOrUntrack;
            this.prevCommandObj = prevCommandObj;
        }
    }

    private static Map<Long, UserState> usersState;
    private static TelegramBot bot;

    @Autowired
    public void init(TelegramBot bot) {
        usersState = new HashMap<>();
        UpdatesProcessor.bot = bot;
        bot.setUpdatesListener(updates -> {
            updates.forEach(update -> {
                log.info(update.toString());
                UpdatesProcessor.process(new UpdateWrapper(update), bot);
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    public static void process(UpdateWrapper update, TelegramBot bot) {
        if (update.message() == null) {
            log.info("No message update");
            return;
        }
        if (!usersState.get(update.chatId()).isTrackOrUntrack) {
            Command command = identifyCommand(update);
            bot.execute(command.handle(update));
        } else {
            bot.execute(usersState.get(update.chatId()).prevCommandObj.handle(update));
            usersState.get(update.chatId()).isTrackOrUntrack = false;
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
                var cur = usersState.get(update.chatId());
                cur.prevCommandObj = new Track();
                cur.isTrackOrUntrack = true;
                yield cur.prevCommandObj;
            }
            case "/untrack" -> {
                var cur = usersState.get(update.chatId());
                cur.prevCommandObj = new Untrack();
                cur.isTrackOrUntrack = true;
                yield cur.prevCommandObj;
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

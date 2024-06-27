package edu.java.bot.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import edu.java.bot.apiwrapper.UpdateWrapper;
import edu.java.bot.commands.Command;
import edu.java.bot.commands.Help;
import edu.java.bot.commands.List;
import edu.java.bot.commands.Start;
import edu.java.bot.commands.Track;
import edu.java.bot.commands.TrackInfo;
import edu.java.bot.commands.Unknown;
import edu.java.bot.commands.Untrack;
import edu.java.bot.commands.UntrackInfo;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdatesProcessor {
    private static Map<Long, UserState> usersState;
    private static TelegramBot bot;
    private static Help help;
    private static List list;
    private static Start start;
    private static Track track;
    private static Unknown unknown;
    private static Untrack untrack;
    private static TrackInfo trackInfo;
    private static UntrackInfo untrackInfo;

    @Autowired
    @SuppressWarnings({"ParameterNumber"})
    public void init(
        TelegramBot bot,
        Help help,
        List list,
        Start start,
        Track track,
        Unknown unknown,
        Untrack untrack,
        TrackInfo trackInfo,
        UntrackInfo untrackInfo,
        MeterRegistry meterRegistry
    ) {
        usersState = new HashMap<>();
        UpdatesProcessor.bot = bot;
        bot.setUpdatesListener(updates -> {
            updates.forEach(update -> {
                log.info(update.toString());
                UpdatesProcessor.process(new UpdateWrapper(update), bot);
                Counter.builder("messages.counter").register(meterRegistry).increment();
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
        UpdatesProcessor.help = help;
        UpdatesProcessor.list = list;
        UpdatesProcessor.start = start;
        UpdatesProcessor.track = track;
        UpdatesProcessor.unknown = unknown;
        UpdatesProcessor.untrack = untrack;
        UpdatesProcessor.trackInfo = trackInfo;
        UpdatesProcessor.untrackInfo = untrackInfo;
    }

    public static void process(UpdateWrapper update, TelegramBot bot) {
        usersState.putIfAbsent(update.chatId(), new UserState(false, null));
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
            case "/start" -> start;
            case "/help" -> help;
            case "/track" -> {
                var cur = usersState.get(update.chatId());
                cur.prevCommandObj = track;
                cur.isTrackOrUntrack = true;
                yield trackInfo;
            }
            case "/untrack" -> {
                var cur = usersState.get(update.chatId());
                cur.prevCommandObj = untrack;
                cur.isTrackOrUntrack = true;
                yield untrackInfo;
            }
            case "/list" -> list;
            default -> unknown;
        };
    }

    private UpdatesProcessor() {
    }

    static class UserState {
        boolean isTrackOrUntrack;
        Command prevCommandObj;

        UserState(boolean isTrackOrUntrack, Command prevCommandObj) {
            this.isTrackOrUntrack = isTrackOrUntrack;
            this.prevCommandObj = prevCommandObj;
        }
    }
}

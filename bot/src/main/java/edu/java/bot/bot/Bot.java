package edu.java.bot.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import edu.java.bot.apiwrapper.UpdateWrapper;
import edu.java.bot.configuration.ApplicationConfig;
import java.util.logging.Logger;

public class Bot implements AutoCloseable {
    private static final ApplicationConfig CONFIG =
        new ApplicationConfig("6965503493:AAFSpi1u2oqaLHbpQ9-TTaD7vro60GJ4Aio");
    private static final Logger LOGGER_BOT = Logger.getLogger("Bot Logger");
    private TelegramBot bot;

    public void startBot() {
        bot = new TelegramBot(CONFIG.telegramToken());
        bot.setUpdatesListener(updates -> {
            updates.forEach(update -> {
                LOGGER_BOT.info(update.toString());
                UpdatesProcessor.process(new UpdateWrapper(update), bot);
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    @Override
    public void close() throws Exception {
        //bot.shutdown();
    }
}

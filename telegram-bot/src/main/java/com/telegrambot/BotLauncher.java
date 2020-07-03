package com.telegrambot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotLauncher {
    static {
        ApiContextInitializer.init();
    }

    /**
     * javaDoc summary.
     */
    public static void launch(TelegramLongPollingBot bot) {
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

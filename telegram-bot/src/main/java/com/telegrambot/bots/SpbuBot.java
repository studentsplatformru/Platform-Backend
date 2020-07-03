package com.telegrambot.bots;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SpbuBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("test message");
            try {
                execute(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "Test_test_test_4Bot";
    }

    @Override
    public String getBotToken() {
        return "1397679751:AAF_WRnIrWSIuJnMbz9XJoh89sveu7MZt8k";
    }
}

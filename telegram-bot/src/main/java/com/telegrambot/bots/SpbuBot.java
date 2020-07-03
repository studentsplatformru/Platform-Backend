package com.telegrambot.bots;

import com.telegrambot.emojis.Emoji;
import com.telegrambot.parsers.SpbuScheduleParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SpbuBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        SpbuScheduleParser parser = new SpbuScheduleParser("https://timetable.spbu.ru/MATH/StudentGroupEvents/Primary/247739");

        if (update.hasMessage() && update.getMessage().hasText()) {
            String emoji = Emoji.SMILE.unicode();
            String request = update.getMessage().getText();
            String timetable = parser.getDailySchedule(request);
            SendMessage message = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText(timetable + emoji);
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

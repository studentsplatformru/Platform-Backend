package ru.studentsplatform.backend.tlgrmbot.bot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import javax.annotation.PostConstruct;


/**
 * Бот принимает текст сообщения и отсылает его обратно пользователю.
 */
@Service
public class StudentsPlatformBot extends TelegramLongPollingBot {

    static {
        ApiContextInitializer.init();
    }

    /**
     * Метод вызывается каждый раз, когда бот получает сообщение.
     * Отправляет сообщение обратно пользователю.
     * @param update Сообщение от пользователя.
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            sendMessageToUser(getText(update), getChatId(update));
        }
    }

    /**
     * Возвращает текст сообщения.
     * @param update сообщение от пользователя.
     * @return Текст сообщения.
     */
    private String getText(Update update) {
        return update.getMessage().getText();
    }

    /**
     * Возвращает Id чата, из которого было отправлено сообщение.
     * @param update сообщение от пользователя.
     * @return Id чата.
     */
    private long getChatId(Update update) {
        return update.getMessage().getChatId();
    }

    /**
     * Отправляет сообщение в чат.
     * @param message Сообщение для отправки пользователю.
     * @param chatId Id чата, в который будет отправлено сообщение.
     */
    private void sendMessageToUser(String message, long chatId) {
        SendMessage sendMessage = new SendMessage().setChatId(chatId).setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //---------------------------------------Служебные методы------------------------------------------//

    @Override
    public String getBotUsername() {
        return "StudPlatformTestBot";
    }

    @Override
    public String getBotToken() {
        return "1334599795:AAG0yj3g1P5E4fwjpTADqoZ706OMNn-DlJQ";
    }

    /**
     * Регистрирует и запускает инстанс бота.
     */
    @PostConstruct
    public void registerBot() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

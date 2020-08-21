package ru.studentsplatform.backend.notification;

/**
 * Вспомогательный класс для отправки сообщения через Telegram.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (08.08.2020).
 */
public interface TelegramSender {

    /**
     * Метод для отправки сообщения через Telegram.
     *
     * @param userId адрес отправки сообщения.
     * @param text сообщение для отправки.
     */
    void sendMessage(String userId, String text);

}

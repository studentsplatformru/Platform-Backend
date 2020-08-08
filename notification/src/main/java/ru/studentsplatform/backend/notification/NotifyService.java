package ru.studentsplatform.backend.notification;

import ru.studentsplatform.backend.notification.enumerated.MessageType;

/**
 * Централизованный сервис для отправки уведомлений пользователям.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
 */
public interface NotifyService {

    /**
     * Метод для для отправки уведомлений пользователям.
     *
     * @param MessageType устанавливается необходимый {@link MessageType}.
     * @param args необходимые для подстановки в шаблон значения.
     * @param user полользователь для отправки.
     */
    void sendNotification(String user, MessageType MessageType, String ...args);

    /**
     * Реализация оправки сообщения черерз Email-сервис.
     *
     * @param MessageType устанавливается необходимый {@link MessageType}.
     * @param args необходимые для подстановки в шаблон значения.
     * @param user полользователь для отправки.
     */
    void sendEmail(String user, MessageType MessageType, String... args);

    /**
     * Реализация оправки сообщения черерз VK-сервис.
     *
     * @param MessageType устанавливается необходимый {@link MessageType}.
     * @param args необходимые для подстановки в шаблон значения.
     * @param user полользователь для отправки.
     */
    void sendVK(String user, MessageType MessageType, String... args);

    /**
     * Реализация оправки сообщения черерз Telegram-сервис.
     *
     * @param MessageType устанавливается необходимый {@link MessageType}.
     * @param args необходимые для подстановки в шаблон значения.
     * @param user полользователь для отправки.
     */
    void sendTelegram(String user, MessageType MessageType, String... args);
}

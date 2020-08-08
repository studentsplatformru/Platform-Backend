package ru.studentsplatform.backend.notification;

import ru.studentsplatform.backend.entities.model.enums.NotificationType;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.notification.enumerated.MessageType;

import java.util.List;

/**
 * Централизованный сервис для отправки уведомлений пользователям.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
 */
public interface NotifyService {

    /**
     * Метод для для отправки уведомлений пользователям.
     *
     * @param messageType устанавливается необходимый {@link MessageType}.
     * @param args необходимые для подстановки в шаблон значения.
     * @param user полользователь для отправки.
     */
    void sendNotification(User user, MessageType messageType, String ...args);

    /**
     * Метод для для отправки уведомлений пользователям.
     *
     * @param notificationTypes получаются необходимые способы
     * отправки уведомления.
     * @param messageType тип сообщения в виде {@link MessageType}.
     * @param args необходимые для подстановки в шаблон значения.
     * @param users полользователи для отправки.
     */
    void sendNotification(List<User> users, List<NotificationType> notificationTypes,
                          MessageType messageType, String ...args);

    /**
     * Реализация оправки сообщения черерз Email-сервис.
     *
     * @param message получает ноебходимое сообщение для отправки.
     * @param args необходимые для подстановки в шаблон значения.
     * @param user полользователь для отправки.
     */
    void sendEmail(User user, String message, String... args);

    /**
     * Реализация оправки сообщения черерз VK-сервис.
     *
     * @param message получает ноебходимое сообщение для отправки.
     * @param args необходимые для подстановки в шаблон значения.
     * @param user полользователь для отправки.
     */
    void sendVK(User user, String message, String... args);

    /**
     * Реализация оправки сообщения черерз Telegram-сервис.
     *
     * @param message получает ноебходимое сообщение для отправки.
     * @param args необходимые для подстановки в шаблон значения.
     * @param user полользователь для отправки.
     */
    void sendTelegram(User user, String message, String... args);
}

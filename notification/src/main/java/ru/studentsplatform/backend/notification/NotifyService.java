package ru.studentsplatform.backend.notification;

import ru.studentsplatform.backend.domain.dto.telegram.TelegramMessageDTO;
import ru.studentsplatform.backend.entities.model.enums.NotificationType;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.notification.enumerated.MessageType;

import java.time.LocalDateTime;
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
     * @param user пользователь для отправки.
     * @param messageType устанавливается необходимый {@link MessageType}.
     * @param args необходимые для подстановки в шаблон значения.
     */
    void sendNotification(User user, MessageType messageType, Object... args);

    /**
     * Метод c определённый типом отправки уведомлений.
     *
     * @param user пользователь для отправки.
     * @param messageType устанавливается необходимый {@link MessageType}.
     * @param notificationType устанавливается необходимый способ отправки уведомления.
     * @param args необходимые для подстановки в шаблон значения.
     */
    void sendNotification(User user, MessageType messageType,
                          NotificationType notificationType,
                          Object... args);
    /**
     * Метод для для отправки уведомлений пользователям.
     *
     * @param user пользователь для отправки.
     * @param notificationTypes получаются необходимые способы
     * отправки уведомления.
     * @param messageType тип сообщения в виде {@link MessageType}.
     * @param args необходимые для подстановки в шаблон значения.
     */
    void sendNotification(User user, List<NotificationType> notificationTypes,
                          MessageType messageType, Object... args);

    /**
     * Метод для для отправки уведомлений пользователям.
     *
     * @param users пользователи для отправки.
     * @param notificationTypes получаются необходимые способы
     * отправки уведомления.
     * @param messageType тип сообщения в виде {@link MessageType}.
     * @param args необходимые для подстановки в шаблон значения.
     */
    void sendNotification(List<User> users, List<NotificationType> notificationTypes,
                          MessageType messageType, Object... args);

    /**
     * Метод для для отправки уведомлений пользователям.
     *
     * @param users пользователи для отправки.
     * @param notificationType способ отправки уведомления.
     * @param messageType тип сообщения в виде {@link MessageType}.
     * @param args необходимые для подстановки в шаблон значения.
     */
    void sendNotification(List<User> users, NotificationType notificationType,
                          MessageType messageType, Object... args);

    /**
     * Метод для для отправки уведомлений пользователям.
     *
     * @param users пользователи для отправки.
     * @param messageType тип сообщения в виде {@link MessageType}.
     * @param args необходимые для подстановки в шаблон значения.
     */
    void sendNotification(List<User> users, MessageType messageType, Object... args);


    /**
     * Метод для для отправки уведомлений пользователям на определённое время.
     *
     * @param user пользователь для отправки.
     * @param messageType устанавливается необходимый {@link MessageType}.
     * @param localDateTime точная дата отправки сообщения.
     * @param args необходимые для подстановки в шаблон значения.
     */
    void sendPlannedByDateNotification(User user, MessageType messageType, LocalDateTime localDateTime, Object... args);

    /**
     * Метод для для отправки уведомлений пользователям на определённое время.
     *
     * @param user пользователь для отправки.
     * @param messageType устанавливается необходимый {@link MessageType}.
     * @param notificationType устанавливается необходимый способ отправки уведомления.
     * @param localDateTime точная дата отправки сообщения.
     * @param args необходимые для подстановки в шаблон значения.
     */
    void sendPlannedByDateNotification(User user, MessageType messageType,
                                     NotificationType notificationType, LocalDateTime localDateTime,
                                     Object... args);
    /**
     * Метод для для отправки уведомлений пользователям на определённое время.
     *
     * @param user пользователь для отправки.
     * @param notificationTypes получаются необходимые способы
     * отправки уведомления.
     * @param messageType тип сообщения в виде {@link MessageType}.
     * @param localDateTime точная дата отправки сообщения.
     * @param args необходимые для подстановки в шаблон значения.
     */
    void sendPlannedByDateNotification(User user, List<NotificationType> notificationTypes,
                                     MessageType messageType, LocalDateTime localDateTime, Object... args);

    /**
     * Метод для для отправки уведомлений пользователям на определённое время.
     *
     * @param users пользователи для отправки.
     * @param notificationTypes получаются необходимые способы
     * отправки уведомления.
     * @param messageType тип сообщения в виде {@link MessageType}.
     * @param localDateTime точная дата отправки сообщения.
     * @param args необходимые для подстановки в шаблон значения.
     */
    void sendPlannedByDateNotification(List<User> users, List<NotificationType> notificationTypes,
                                     MessageType messageType, LocalDateTime localDateTime, Object... args);

    /**
     * Метод для для отправки уведомлений пользователям на определённое время.
     *
     * @param users пользователи для отправки.
     * @param notificationType способ отправки уведомления.
     * @param messageType тип сообщения в виде {@link MessageType}.
     * @param localDateTime точная дата отправки сообщения.
     * @param args необходимые для подстановки в шаблон значения.
     */
    void sendPlannedByDateNotification(List<User> users, NotificationType notificationType,
                                      MessageType messageType, LocalDateTime localDateTime, Object... args);

    /**
     * Метод для для отправки уведомлений пользователям на определённое время.
     *
     * @param users пользователи для отправки.
     * @param messageType тип сообщения в виде {@link MessageType}.
     * @param localDateTime точная дата отправки сообщения.
     * @param args необходимые для подстановки в шаблон значения.
     */
    void sendPlannedByDateNotification(List<User> users, MessageType messageType,
                                     LocalDateTime localDateTime, Object... args);

    /**
     * Метод для отправки пакета сообщений telegram боту.
     * Реализован для "будильника".
     * @param messages список сообщений для Telegram.
     */
    void sendListMessagesTelegram(List<TelegramMessageDTO> messages);
}

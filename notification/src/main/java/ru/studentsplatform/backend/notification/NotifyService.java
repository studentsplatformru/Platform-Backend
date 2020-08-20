package ru.studentsplatform.backend.notification;

import ru.studentsplatform.backend.entities.model.enums.NotificationType;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.notification.enumerated.MessageType;

import java.util.Date;
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
     * Метод для для отправки уведомлений пользователям.
     *
     * @param user пользователь для отправки.
     * @param messageType устанавливается необходимый {@link MessageType}.
     * @param date точная дата отправки сообщения.
     * @param args необходимые для подстановки в шаблон значения.
     */
    void sendSpecificDayNotification(User user, MessageType messageType, Date date, Object... args);

    /**
     * Метод c определённый типом отправки уведомлений.
     *
     * @param user пользователь для отправки.
     * @param messageType устанавливается необходимый {@link MessageType}.
     * @param notificationType устанавливается необходимый способ отправки уведомления.
     * @param date точная дата отправки сообщения.
     * @param args необходимые для подстановки в шаблон значения.
     */
    void sendSpecificDayNotification(User user, MessageType messageType,
                                     NotificationType notificationType, Date date,
                                     Object... args);
    /**
     * Метод для для отправки уведомлений пользователям.
     *
     * @param user пользователь для отправки.
     * @param notificationTypes получаются необходимые способы
     * отправки уведомления.
     * @param messageType тип сообщения в виде {@link MessageType}.
     * @param date точная дата отправки сообщения.
     * @param args необходимые для подстановки в шаблон значения.
     */
    void sendSpecificDayNotification(User user, List<NotificationType> notificationTypes,
                                     MessageType messageType, Date date, Object... args);

    /**
     * Метод для для отправки уведомлений пользователям.
     *
     * @param users пользователи для отправки.
     * @param notificationTypes получаются необходимые способы
     * отправки уведомления.
     * @param messageType тип сообщения в виде {@link MessageType}.
     * @param date точная дата отправки сообщения.
     * @param args необходимые для подстановки в шаблон значения.
     */
    void sendSpecificDayNotification(List<User> users, List<NotificationType> notificationTypes,
                                     MessageType messageType, Date date, Object... args);

    /**
     * Метод для для отправки уведомлений пользователям.
     *
     * @param users пользователи для отправки.
     * @param notificationType способ отправки уведомления.
     * @param messageType тип сообщения в виде {@link MessageType}.
     * @param date точная дата отправки сообщения.
     * @param args необходимые для подстановки в шаблон значения.
     */
    void sendSpecificDayNotification(List<User> users, NotificationType notificationType,
                                      MessageType messageType, Date date, Object... args);

    /**
     * Метод для для отправки уведомлений пользователям.
     *
     * @param users пользователи для отправки.
     * @param messageType тип сообщения в виде {@link MessageType}.
     * @param date точная дата отправки сообщения.
     * @param args необходимые для подстановки в шаблон значения.
     */
    void sendSpecificDayNotification(List<User> users, MessageType messageType,
                                     Date date, Object... args);

}

package ru.studentsplatform.backend.notification;

import ru.studentsplatform.backend.notification.enumerated.MessageType;

/**
 * Централизованный сервис для отправки уведомлений пользователям.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
 */
public interface NotifyController {

    void sendNotification(String user, MessageType type, String ...args);

}
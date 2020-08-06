package ru.studentsplatform.backend.notification;

import ru.studentsplatform.backend.entities.model.user.User;

public interface NotifyController {

    void sendNotification(String user, MessageType type, String ...args);

}

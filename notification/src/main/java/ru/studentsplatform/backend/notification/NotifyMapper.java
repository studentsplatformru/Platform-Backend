package ru.studentsplatform.backend.notification;

import ru.studentsplatform.backend.entities.model.user.User;

public interface NotifyMapper {

    void sendNotification(User user, MessageType type);

}

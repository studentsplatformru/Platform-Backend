package ru.studentsplatform.backend.notification.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.studentsplatform.backend.entities.model.enums.NotificationType;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.notification.EMailSender;
import ru.studentsplatform.backend.notification.NotifyMapper;
import ru.studentsplatform.backend.notification.MessageType;

import java.io.IOException;

public class NotifyMapperImpl implements NotifyMapper {

    @Autowired
    private EMailSender eMailSender;

    @Override
    public void sendNotification(User user, MessageType messageType) {

        NotificationType notificationType = user.getNotification_type();

        switch (notificationType){
            case EMAIL: sendEmail(user.getEmail(), messageType); break;
            case VK: sendVK(user.getVk_id(), messageType); break;
            case TELEGRAM:sendTelegram(user.getTelegram_id(), messageType); break;
        }

    }


    private void sendEmail(String mail, MessageType messageType){
        try {
            eMailSender.sendHtml(mail,
                    "Students Platform",
                    "src/main/resources/templates/" + messageType + ".html",
                    null);
        } catch (IOException ignored) {

        }
    }

    private void sendVK(String userId, MessageType messageType){
        try {
            throw new NoSuchMethodException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void sendTelegram(String userId, MessageType messageType){
        try {
            throw new NoSuchMethodException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}

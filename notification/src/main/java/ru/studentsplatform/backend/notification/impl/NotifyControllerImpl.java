package ru.studentsplatform.backend.notification.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.entities.model.enums.NotificationType;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.notification.EMailSender;
import ru.studentsplatform.backend.notification.NotifyController;
import ru.studentsplatform.backend.notification.MessageType;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Service
public class NotifyControllerImpl implements NotifyController {

    @Autowired
    private EMailSender eMailSender;

    @Override
    public void sendNotification(String user, MessageType messageType, String ...args) {

//        String notificationType = user.getNotificationType().name();
        String notificationType = "Email";

        try {
            this.getClass()
                    .getDeclaredMethod("send" + notificationType,
//                            User.class,
                            String.class,
                            MessageType.class, String[].class )
                    .invoke(this, user, messageType, args);

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private void sendEmail(String user, MessageType messageType, String ...args){


        try {
            eMailSender.sendHtml(user,
                    "Students Platform",

                    "notification\\src\\main\\resources\\templates\\" + messageType + ".html",
                    null);
        } catch (IOException ignored) {

        }
    }

    private void sendVK(User user, MessageType messageType, String ...args){
        try {
            throw new NoSuchMethodException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void sendTelegram(User user, MessageType messageType, String ...args){
        try {

            throw new NoSuchMethodException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}

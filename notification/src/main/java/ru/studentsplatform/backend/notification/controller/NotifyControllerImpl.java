package ru.studentsplatform.backend.notification.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.notification.EMailSender;
import ru.studentsplatform.backend.notification.enumerated.MessageType;
import ru.studentsplatform.backend.notification.NotifyController;
import ru.studentsplatform.backend.notification.EmailTemplateService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Реализация {@link NotifyController}.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
 */
@Service
public class NotifyControllerImpl implements NotifyController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EMailSender eMailSender;

    @Autowired
    private EmailTemplateService templateService;

    /**
     * {@inheritDoc}
     */
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

            logger.error(Arrays.toString(e.getStackTrace()));

        }

    }

    /**
     * Реализация оправки сообщения черерз Email-сервис
     */
    private void sendEmail(String user, MessageType messageType, String ...args){

        String html = templateService.getEmailTemplate(messageType, args);

        try {
            eMailSender.sendHtml(user,
                    "Students Platform",
                    html,
                    null);
        } catch (IOException ignored) {

        }
    }

    /**
     * Реализация оправки сообщения черерз VK-сервис
     */
    private void sendVK(User user, MessageType messageType, String ...args){
        try {
            throw new NoSuchMethodException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * Реализация оправки сообщения черерз Telegram-сервис
     */
    private void sendTelegram(User user, MessageType messageType, String ...args){
        try {

            throw new NoSuchMethodException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}

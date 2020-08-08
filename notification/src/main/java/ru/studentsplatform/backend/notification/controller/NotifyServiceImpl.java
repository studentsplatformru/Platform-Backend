package ru.studentsplatform.backend.notification.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.entities.model.enums.NotificationType;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.notification.BotTemplateService;
import ru.studentsplatform.backend.notification.EMailSender;
import ru.studentsplatform.backend.notification.EmailTemplateService;
import ru.studentsplatform.backend.notification.NotifyService;
import ru.studentsplatform.backend.notification.enumerated.MessageType;

import java.io.IOException;

/**
 * Реализация {@link NotifyService}.
 * Использует выбранный сервис исходя из приоритета пользователя.
 * Сообщение обрабатывается и отправляется согласно выбраному типу {@link MessageType}.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
 */
@Service
public class NotifyServiceImpl implements NotifyService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EMailSender eMailSender;

    @Autowired
    private EmailTemplateService templateService;

    @Autowired
    private BotTemplateService botTemplateService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendNotification(String user, MessageType messageType, String ...args) {

//        String notificationType = user.getNotificationType().name();
        NotificationType notificationType = NotificationType.Email;


        if (notificationType == NotificationType.Email) {

            this.sendEmail(user, messageType, args);

        }else if (notificationType == NotificationType.Telegram) {

            this.sendTelegram(user, messageType, args);

        }else if (notificationType == NotificationType.VK) {

            this.sendVK(user, messageType, args);

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendEmail(String user, MessageType messageType, String... args){

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
     * {@inheritDoc}
     */
    @Override
    public void sendVK(String user, MessageType messageType, String... args){

        // Получение адреса отправки user.getVkId()
        String template = botTemplateService.getBotTemplate(messageType, args);
        try {

            throw new NoSuchMethodException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendTelegram(String user, MessageType messageType, String... args){

        // Получение адреса отправки user.getTelegramId()
        String template = botTemplateService.getBotTemplate(messageType, args);
        try {

            throw new NoSuchMethodException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}

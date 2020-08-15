package ru.studentsplatform.backend.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.entities.model.enums.NotificationType;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.notification.BotTemplateService;
import ru.studentsplatform.backend.notification.EMailSender;
import ru.studentsplatform.backend.notification.HtmlTemplateService;
import ru.studentsplatform.backend.notification.NotifyService;
import ru.studentsplatform.backend.notification.enumerated.MessageType;

import java.io.IOException;
import java.util.List;

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

    private final EMailSender eMailSender;

    private final HtmlTemplateService htmlTemplateService;

    private final BotTemplateService botTemplateService;


    /**
     * @param eMailSender сервис для отправки email сообщений.
     * @param htmlTemplateService сервис для обработки html уведомлений.
     * @param botTemplateService сервис для обработки строковых уведомлений.
     */
    public NotifyServiceImpl(
            EMailSender eMailSender,
            HtmlTemplateService htmlTemplateService,
            BotTemplateService botTemplateService) {
        this.eMailSender = eMailSender;
        this.htmlTemplateService = htmlTemplateService;
        this.botTemplateService = botTemplateService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendNotification(User user, MessageType messageType, String... args) {

        this.sendNotification(user, messageType, user.getNotificationType(), args);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendNotification(User user, MessageType messageType,
                                 NotificationType notificationType,
                                 String... args) {
        String message;

        if (notificationType == NotificationType.Email) {

            message = htmlTemplateService.getHtmlTemplate(messageType, args);

            this.sendEmail(user, message, args);

        } else if (notificationType == NotificationType.Telegram) {

            message = botTemplateService.getBotTemplate(messageType, args);

            this.sendTelegram(user, message, args);

        } else if (notificationType == NotificationType.VK) {

            message = botTemplateService.getBotTemplate(messageType, args);

            this.sendVK(user, message, args);

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendNotification(List<User> users,
                                 List<NotificationType> notificationTypes,
                                 MessageType messageType, String... args) {

        String message = htmlTemplateService.getHtmlTemplate(messageType, args);
        String botMessage = botTemplateService.getBotTemplate(messageType, args);

        for (User user : users) {

            if (notificationTypes.contains(NotificationType.Email)) {

                this.sendEmail(user, message, args);
            }
            if (notificationTypes.contains(NotificationType.Telegram)) {

                this.sendTelegram(user, botMessage, args);

            }
            if (notificationTypes.contains(NotificationType.VK)) {

                this.sendVK(user, botMessage, args);

            }
        }
    }

    private void sendEmail(User user, String message, String... args) {

        try {
            eMailSender.sendHtml(
                    user.getEmail(),
                    "Students Platform",
                    message,
                    null);
        } catch (IOException ignored) {

            logger.error("Отправка сообщения не удалась");
        }
    }

    private void sendVK(User user, String message, String... args) {

        // Получение адреса отправки user.getVkId()
        try {

            // telegramSender.sendMessage(user.getVkId(), message);
            throw new NoSuchMethodException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void sendTelegram(User user, String message, String... args) {

        // Получение адреса отправки user.getTelegramId()
        try {

            // telegramSender.sendMessage(user.getTelegramId(), message);
            throw new NoSuchMethodException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}

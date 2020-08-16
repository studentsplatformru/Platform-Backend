package ru.studentsplatform.backend.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.entities.model.enums.NotificationType;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.notification.EMailSender;
import ru.studentsplatform.backend.notification.TemplateService;
import ru.studentsplatform.backend.notification.NotifyService;
import ru.studentsplatform.backend.notification.enumerated.MessageType;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Реализация {@link NotifyService}.
 * Использует выбранный сервис исходя из приоритета пользователя.
 * Сообщение обрабатывается и отправляется согласно выбраному типу {@link MessageType}.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
 */
@Profiled
@Service
public class NotifyServiceImpl implements NotifyService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EMailSender eMailSender;

    private final TemplateService templateService;


    /**
     * @param eMailSender сервис для отправки email сообщений.
     * @param templateService сервис для обработки шаблона уведомлений.
     */
    public NotifyServiceImpl(
            EMailSender eMailSender,
            TemplateService templateService) {
        this.eMailSender = eMailSender;
        this.templateService = templateService;
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
        this.sendNotification(Collections.singletonList(user),
                Collections.singletonList(notificationType),
                messageType, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendNotification(User user,
                                 List<NotificationType> notificationTypes,
                                 MessageType messageType, String... args) {
        this.sendNotification(Collections.singletonList(user), notificationTypes, messageType, args);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendNotification(List<User> users,
                                 List<NotificationType> notificationTypes,
                                 MessageType messageType, String... args) {

        String message = templateService.getTemplate(messageType, NotificationType.Email, args);
        String botMessage = templateService.getTemplate(messageType, NotificationType.Telegram, args);

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendNotification(List<User> users,
                                 NotificationType notificationType,
                                 MessageType messageType, String... args) {

        this.sendNotification(users, Collections.singletonList(notificationType), messageType, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendNotification(List<User> users,
                                 MessageType messageType, String... args) {

        String message = templateService.getTemplate(messageType, NotificationType.Email, args);
        String botMessage = templateService.getTemplate(messageType, NotificationType.Telegram, args);

        for (User user : users) {

            if (user.getNotificationType() == NotificationType.Email) {

                this.sendEmail(user, message, args);
            }
            if (user.getNotificationType() == NotificationType.Telegram) {

                this.sendTelegram(user, botMessage, args);

            }
            if (user.getNotificationType() == NotificationType.VK) {

                this.sendVK(user, botMessage, args);

            }
        }
    }

    // отправка сообщения через email
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

    // отправка сообщения через vk-бота
    private void sendVK(User user, String message, String... args) {

        // Получение адреса отправки user.getVkId()
        try {

            // VKSender.sendMessage(user.getVkId(), message, args);
            throw new NoSuchMethodException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    // отправка сообщения через telegram-бота
    private void sendTelegram(User user, String message, String... args) {

        // Получение адреса отправки user.getTelegramId()
        try {

            // telegramSender.sendMessage(user.getTelegramId(), message, args);
            throw new NoSuchMethodException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}

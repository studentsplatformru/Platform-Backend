package ru.studentsplatform.backend.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.domain.dto.telegram.TelegramMessageDTO;
import ru.studentsplatform.backend.entities.model.enums.NotificationType;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.notification.EMailSender;
import ru.studentsplatform.backend.notification.NotifyService;
import ru.studentsplatform.backend.notification.TelegramSender;
import ru.studentsplatform.backend.notification.TemplateService;
import ru.studentsplatform.backend.notification.enumerated.MessageType;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;
import ru.studentsplatform.backend.system.manager.JobManager;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Реализация {@link NotifyService}.
 * Использует выбранный сервис исходя из приоритета пользователя.
 * Сообщение обрабатывается и отправляется согласно выбранному типу {@link MessageType}.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
 */
@Profiled
@Service
public class NotifyServiceImpl implements NotifyService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EMailSender eMailSender;

    private final TemplateService templateService;

    private final JobManager jobManager;

    private final TelegramSender telegramSender;


    /**
     * @param eMailSender сервис для отправки email сообщений.
     * @param templateService сервис для обработки шаблона уведомлений.
     * @param jobManager компонент для задержки отправки уведомления.
     * @param telegramSender сервис для отправки сообщений через Telegram
     */
    public NotifyServiceImpl(
            EMailSender eMailSender,
            TemplateService templateService,
            JobManager jobManager,
            TelegramSender telegramSender) {
        this.eMailSender = eMailSender;
        this.templateService = templateService;
        this.jobManager = jobManager;
        this.telegramSender = telegramSender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendNotification(User user, MessageType messageType, Object... args) {
        this.sendNotification(user, messageType, user.getNotificationType(), args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendNotification(User user, MessageType messageType,
                                 NotificationType notificationType,
                                 Object... args) {
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
                                 MessageType messageType, Object... args) {
        this.sendNotification(Collections.singletonList(user), notificationTypes, messageType, args);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendNotification(List<User> users,
                                 List<NotificationType> notificationTypes,
                                 MessageType messageType, Object... args) {

        String message = templateService.getTemplate(messageType, NotificationType.Email, args);
        String botMessage = templateService.getTemplate(messageType, NotificationType.Telegram, args);

        for (User user : users) {

            if (notificationTypes.contains(NotificationType.Email)) {

                this.sendEmail(user, message);
            }
            if (notificationTypes.contains(NotificationType.Telegram)) {

                this.sendTelegram(user, botMessage);

            }
            if (notificationTypes.contains(NotificationType.VK)) {

                this.sendVK(user, botMessage);

            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendNotification(List<User> users,
                                 NotificationType notificationType,
                                 MessageType messageType, Object... args) {

        this.sendNotification(users, Collections.singletonList(notificationType), messageType, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendNotification(List<User> users,
                                 MessageType messageType, Object... args) {

        String message = templateService.getTemplate(messageType, NotificationType.Email, args);
        String botMessage = templateService.getTemplate(messageType, NotificationType.Telegram, args);

        for (User user : users) {

            if (user.getNotificationType() == NotificationType.Email) {

                this.sendEmail(user, message);
            }
            if (user.getNotificationType() == NotificationType.Telegram) {

                this.sendTelegram(user, botMessage);

            }
            if (user.getNotificationType() == NotificationType.VK) {

                this.sendVK(user, botMessage);

            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendPlannedByDateNotification(User user, MessageType messageType,
                                              LocalDateTime localDateTime, Object... args) {
        jobManager.handle(() -> this.sendNotification(user, messageType, args), localDateTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendPlannedByDateNotification(User user, MessageType messageType,
                                              NotificationType notificationType,
                                              LocalDateTime localDateTime, Object... args) {
        jobManager.handle(() -> this.sendNotification(user, messageType, notificationType, args),
                localDateTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendPlannedByDateNotification(User user, List<NotificationType> notificationTypes,
                                              MessageType messageType, LocalDateTime localDateTime,
                                              Object... args) {
        jobManager.handle(
                () -> this.sendNotification(user, notificationTypes, messageType, args), localDateTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendPlannedByDateNotification(List<User> users, List<NotificationType> notificationTypes,
                                              MessageType messageType, LocalDateTime localDateTime,
                                              Object... args) {
        jobManager.handle(
                () -> this.sendNotification(users, notificationTypes, messageType, args), localDateTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendPlannedByDateNotification(List<User> users, NotificationType notificationType,
                                              MessageType messageType, LocalDateTime localDateTime,
                                              Object... args) {
        jobManager.handle(
                () -> this.sendNotification(users, messageType, args), localDateTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendPlannedByDateNotification(List<User> users, MessageType messageType,
                                              LocalDateTime localDateTime, Object... args) {
        jobManager.handle(() -> this.sendNotification(users, messageType, args), localDateTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendListMessagesTelegram(List<TelegramMessageDTO> messages){
        telegramSender.sendMessage(messages);
    }

    // отправка сообщения через email
    private void sendEmail(User user, String message) {

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
    private void sendVK(User user, String message) {

        // Получение адреса отправки user.getVkId()
        try {

            // VKSender.sendMessage(user.getVkId(), message, args);
            throw new NoSuchMethodException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    // отправка сообщения через telegram-бота
    private void sendTelegram(User user, String message) {

        TelegramMessageDTO messageDTO = new TelegramMessageDTO();
        messageDTO.setId(user.getTelegramId());
        messageDTO.setText(message);

        telegramSender.sendMessage(messageDTO);

    }

}

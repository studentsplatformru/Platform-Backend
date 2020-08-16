package ru.studentsplatform.backend.notification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.entities.model.enums.NotificationType;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.notification.enumerated.MessageType;
import ru.studentsplatform.backend.notification.service.NotifyServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Тесты для {@link NotifyServiceImpl}
 *
 * @author Danila K (karnacevich5323537@gmail.com) (15.08.2020).
 */
@ExtendWith(MockitoExtension.class)
public class NotifyServiceTest {

    @Mock
    private EMailSender eMailSender;

    @Mock
    private TemplateService templateService;

    @InjectMocks
    private NotifyServiceImpl notifyService;

    /**
     * Тестируется отправка сообщения через email-сервис.
     *
     * @throws IOException в случае неправильного пути.
     */
    @Test
    public void testEmailNotification() throws IOException {
        User user = new User();
        user.setNotificationType(NotificationType.Email);
        user.setEmail("someemail@email.com");

        doReturn("content path")
                .when(templateService)
                .getTemplate(MessageType.EMAIL_CONFIRMATION, NotificationType.Email,
                        "some test");

        notifyService.sendNotification(user, MessageType.EMAIL_CONFIRMATION, "some test");

        verify(templateService, Mockito.times(1))
                .getTemplate(MessageType.EMAIL_CONFIRMATION, NotificationType.Email,
                        "some test");

        verify(eMailSender, Mockito.times(1))
                .sendHtml(
                        eq("someemail@email.com"),
                        eq("Students Platform"),
                        eq("content path"),
                        eq(null)
                );

    }

    /**
     * Тестируется отправка сообщения через email-сервис.
     *
     * @throws IOException в случае неправильного пути.
     */
    @Test
    public void testEmailNotificationWithManyUsers() throws IOException {

        List<User> users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setNotificationType(NotificationType.Email);
            user.setEmail("someemail@email.com");
            users.add(user);
        }

        List<NotificationType> types = new ArrayList<>();

        types.add(NotificationType.Email);
        types.add(NotificationType.Telegram);

        doReturn("content path")
                .when(templateService)
                .getTemplate(MessageType.EMAIL_CONFIRMATION, NotificationType.Email,
                        "some test");

        notifyService.sendNotification(users, types, MessageType.EMAIL_CONFIRMATION, "some test");

        verify(templateService, Mockito.times(1))
                .getTemplate(MessageType.EMAIL_CONFIRMATION, NotificationType.Email,
                        "some test");

        verify(templateService, Mockito.times(1))
                .getTemplate(MessageType.EMAIL_CONFIRMATION, NotificationType.Telegram,
                        "some test");

        verify(eMailSender, Mockito.times(10))
                .sendHtml(
                        eq("someemail@email.com"),
                        eq("Students Platform"),
                        eq("content path"),
                        eq(null)
                );

        // will throw NoSuchMethodException
        // + add telegram realization
    }

    /**
     * Тестируется отправка сообщения через Telegram-сервис.
     */
    @Test
    public void testBotNotification() {
        User user = new User();
        user.setNotificationType(NotificationType.Telegram);
        user.setTelegramId("/someid");

        notifyService.sendNotification(user, MessageType.EMAIL_CONFIRMATION, "some test");

        verify(templateService, Mockito.times(1))
                .getTemplate(MessageType.EMAIL_CONFIRMATION, NotificationType.Telegram,
                        "some test");

        // will throw NoSuchMethodException
        // + add telegram realization
    }

}

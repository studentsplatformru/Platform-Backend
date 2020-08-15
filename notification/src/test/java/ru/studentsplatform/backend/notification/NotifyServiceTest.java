package ru.studentsplatform.backend.notification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.entities.model.enums.NotificationType;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.notification.enumerated.MessageType;
import ru.studentsplatform.backend.notification.service.NotifyServiceImpl;

import java.io.IOException;

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
    private HtmlTemplateService htmlTemplateService;

    @Mock
    private BotTemplateService botTemplateService;

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
                .when(htmlTemplateService)
                .getHtmlTemplate(MessageType.EMAIL_CONFIRMATION, "some test");

        notifyService.sendNotification(user, MessageType.EMAIL_CONFIRMATION, "some test");

        verify(htmlTemplateService, Mockito.times(1))
                .getHtmlTemplate(MessageType.EMAIL_CONFIRMATION, "some test");

        verify(eMailSender, Mockito.times(1))
                .sendHtml(
                        eq("someemail@email.com"),
                        eq("Students Platform"),
                        eq("content path"),
                        eq(null)
                );

    }

    /**
     * Тестируется отправка сообщения через Telegram-сервис.
     *
     */
    @Test
    public void testBotNotification() {
        User user = new User();
        user.setNotificationType(NotificationType.Telegram);
        user.setTelegramId("/someid");

        notifyService.sendNotification(user, MessageType.EMAIL_CONFIRMATION, "some test");

        verify(botTemplateService, Mockito.times(1))
                .getBotTemplate(MessageType.EMAIL_CONFIRMATION, "some test");

        // will throw NoSuchMethodException
    }

}

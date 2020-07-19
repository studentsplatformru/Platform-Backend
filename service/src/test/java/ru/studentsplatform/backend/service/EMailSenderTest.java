package ru.studentsplatform.backend.service;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;
import ru.studentsplatform.backend.service.emailinterface.EMailSender;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;


/**
 * Тесты для {@link EMailSender}
 */
@ExtendWith(MockitoExtension.class)
public class EMailSenderTest {

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private MimeMessage mimeMessage;

    @InjectMocks
    private EMailSenderImpl eMailSender;

    /**
     * Тест на простую отправку сообщения.
     */
    @Test
    public void sendMailTest() {
        String subject = "Some subject";
        String body = "Some contents.";
        String to = "test@test.com";

        eMailSender.send(to, subject, body);

        verify(javaMailSender, Mockito.times(1))
                .send((SimpleMailMessage) ArgumentMatchers.any());

    }

    /**
     * Тест отправки сообщения со вложениями.
     * @throws IOException в случае неправильного пути файла.
     */
    @Test
    public void sendMailTestWithContent() throws IOException {
        ReflectionTestUtils.setField(eMailSender, "from", "someadress@gmail.com");

        String subject = "Some subject";
        String body = "Some contents.";
        String to = "test@test.com";
        String contentPath = "src/test/java/ru/studentsplatform/backend/service/resources/test.txt";


        doReturn(mimeMessage)
                .when(javaMailSender)
                .createMimeMessage();

        eMailSender.send(to, subject, body, contentPath);

        verify(javaMailSender, Mockito.times(1))
                .createMimeMessage();

        verify(javaMailSender, Mockito.times(1))
                .send((MimeMessage) ArgumentMatchers.any());

    }

    /**
     * Тест с выдачей {@link NoSuchFileException} т.к. не найдёт нужный файл.
     */
    @Test
    public void sendMailTestWithContentFail() {
        ReflectionTestUtils.setField(eMailSender, "from", "someadress@gmail.com");
        String subject = "Some subject";
        String body = "Some contents.";
        String to = "test@test.com";
        String contentPath = "/some/wrong/path";

        doReturn(mimeMessage)
                .when(javaMailSender)
                .createMimeMessage();

        assertThrows(NoSuchFileException.class,
                () -> eMailSender.send(to, subject, body, contentPath));

        verify(javaMailSender, Mockito.times(1))
                .createMimeMessage();

        verify(javaMailSender, Mockito.times(0))
                .send((MimeMessage) ArgumentMatchers.any());
    }
}
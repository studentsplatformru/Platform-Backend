package ru.studentsplatform.backend.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;


/**
 * Тесты для {@link ru.studentsplatform.backend.service.EMailSender}
 */
@SpringBootTest
public class EMailSenderTest {

    @Autowired
    private EMailSender eMailSender;

    @MockBean
    private JavaMailSender javaMailSender;

    @MockBean
    private MimeMessage mimeMessage;

    /**
     * Тест на простую отправку сообщения
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
     * Тест отправки сообщения со вложениями
     * @throws IOException в случае неправильного пути файла
     */
    @Test
    public void sendMailTestWithContent() throws IOException {
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
     * Тест с выдачей {@link NoSuchFileException} т.к. не найдёт нужный файл
     */
    @Test
    public void sendMailTestWithContentFail() {
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


    /**
     * Необходимая добавка для поднятия контекста и подгрузки тестируемого бина
     */
    @SpringBootApplication
    static class TestConfiguration{

    }

}
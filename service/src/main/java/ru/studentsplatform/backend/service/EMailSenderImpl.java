package ru.studentsplatform.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.emailinterface.EMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


/**
 * Реализация {@link EMailSender}
 * Адресс отправки устанавливается в конфигурациях.
 * @author Danila K (karnacevich5323537@gmail.com) (10.07.2020).
 * */
@Service
public class EMailSenderImpl  implements EMailSender {

    /**
     * Адрес отправки.
     */
    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender javaMailSender;

    /**
     * @param javaMailSender утлилитный сприноговая реализация,
     * которая обеспечивает логику отправки сообщения на более низком уровне.
     */
    public EMailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(@NonNull String to, String subject, String body) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        javaMailSender.send(mailMessage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(
            @NonNull String to, String subject,
            String body, String contentPath) throws IOException {

        MimeMessage message = javaMailSender.createMimeMessage();

        try {

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);

            Path path = Paths.get(contentPath);
            byte[] content = Files.readAllBytes(path);

            helper.addAttachment(
                    path.getFileName().toString(),
                    new ByteArrayResource(content));

            javaMailSender.send(message);

        } catch (MessagingException e) {

            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(
            @NonNull String to, String subject,
            String body, List<String> contentPaths) throws IOException {

        MimeMessage message = javaMailSender.createMimeMessage();

        try {

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);

            for (String contentPath : contentPaths){
                Path path = Paths.get(contentPath);
                byte[] content = Files.readAllBytes(path);

                helper.addAttachment(
                        path.getFileName().toString(),
                        new ByteArrayResource(content));
            }

            javaMailSender.send(message);

        } catch (MessagingException e) {

            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendHtml(
            @NonNull String to, String subject,
            String htmlPath, @Nullable List<String> contentPaths) throws IOException {

        MimeMessage message = javaMailSender.createMimeMessage();

        try {

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            Scanner scanner = new Scanner(
                    Paths.get(htmlPath),
                    StandardCharsets.UTF_8.name());
            //здесь мы можем использовать разделитель, например: "\\A", "\\Z" или "\\z"
            String html = scanner.useDelimiter("\\A").next();
            scanner.close();

            message.setSubject(subject, "UTF-8");
            message.setContent(html, "text/html; charset=UTF-8");

            helper.setFrom(from);
            helper.setTo(to);

            if (contentPaths != null) {
                for (String contentPath : contentPaths) {
                    Path path = Paths.get(contentPath);
                    byte[] content = Files.readAllBytes(path);

                    helper.addAttachment(
                            path.getFileName().toString(),
                            new ByteArrayResource(content));
                }
            }

            javaMailSender.send(message);

        } catch (MessagingException e) {

            e.printStackTrace();
        }
    }

}

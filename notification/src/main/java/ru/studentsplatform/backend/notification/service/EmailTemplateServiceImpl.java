package ru.studentsplatform.backend.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.notification.EMailSender;
import ru.studentsplatform.backend.notification.MessageType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Реализация {@link EmailTemplateService}
 * Обрабатывает аргументы по типу сообщения и
 * возвращает необходимый шаблон.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
 */
@Service
public class EmailTemplateServiceImpl implements EmailTemplateService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEmailTemplate(MessageType type, String... args) {

        if (type.getParameterCount() != args.length)
            throw new IllegalArgumentException("Неправильное количство элементов");

        try(Scanner scanner = new Scanner(
                Paths.get(type.getPath()),
                StandardCharsets.UTF_8.name())) {

            //здесь мы можем использовать разделитель, например: "\\A", "\\Z" или "\\z"
            String html = scanner.useDelimiter("\\A").next().replaceAll("0%", "&&");

            return String.format(html, args).replaceAll("&&", "0%");

        } catch (IOException e) {

            logger.error(Arrays.toString(e.getStackTrace()));

        }

        return null;
    }
}

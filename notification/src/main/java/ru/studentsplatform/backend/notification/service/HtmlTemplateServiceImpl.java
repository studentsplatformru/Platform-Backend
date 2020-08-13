package ru.studentsplatform.backend.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.notification.HtmlTemplateService;
import ru.studentsplatform.backend.notification.enumerated.MessageType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Реализация {@link HtmlTemplateService}
 * Обрабатывает аргументы по типу сообщения и
 * возвращает необходимый шаблон в виде html.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
 */
@Service
public class HtmlTemplateServiceImpl implements HtmlTemplateService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHtmlTemplate(MessageType type, String... args) {

        if (type.getParameterCount() != args.length) {
            throw new IllegalArgumentException("Неправильное количство элементов");
        }

        try (Scanner scanner = new Scanner(
                Paths.get(type.getPath()),
                StandardCharsets.UTF_8.name())) {

            //здесь мы можем использовать разделитель, например: "\\A", "\\Z" или "\\z"
            // Реализован своебразный костыль, т.к. нет возможности использовать String.format();
            String html = scanner.useDelimiter("\\A").next().replaceAll("0%", "&&");

            return String.format(html, args).replaceAll("&&", "0%");

        } catch (IOException e) {

            logger.error("Неверно указан путь к шаблону сообщения");
            logger.error(Arrays.toString(e.getStackTrace()));

        }

        return null;
    }
}

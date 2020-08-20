package ru.studentsplatform.backend.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.entities.model.enums.NotificationType;
import ru.studentsplatform.backend.notification.Template;
import ru.studentsplatform.backend.notification.TemplateService;
import ru.studentsplatform.backend.notification.enumerated.MessageType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Реализация {@link TemplateService}
 * Обрабатывает аргументы по типу сообщения и
 * возвращает необходимый шаблон в виде html.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTemplate(MessageType type, NotificationType notificationType, Object... args) {

        if (type.getParameterCount() != args.length) {
            throw new IllegalArgumentException("Неправильное количество элементов");
        }

        Template template = type.getTemplateClass();

        // выдаёт сообщение для отправки через ботов
        if (notificationType == NotificationType.Telegram || notificationType == NotificationType.VK) {
            return template.getBotTemplate(args);
        }

        return template.getHtmlTemplate(args);

    }
}

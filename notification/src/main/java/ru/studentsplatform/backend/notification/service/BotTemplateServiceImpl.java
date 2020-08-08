package ru.studentsplatform.backend.notification.service;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.notification.BotTemplateService;
import ru.studentsplatform.backend.notification.enumerated.MessageType;

/**
 * Реализация {@link BotTemplateService}
 * Обрабатывает аргументы по типу сообщения и
 * возвращает необходимый шаблон в виде html.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (08.08.2020).
 */
@Service
public class BotTemplateServiceImpl implements BotTemplateService {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBotTemplate(MessageType type, String... args) {

        if (type.getParameterCount() != args.length){
            throw new IllegalArgumentException("Неправильное количство элементов");
        }

        return String.format(type.getBotPattern(), args);

    }
}

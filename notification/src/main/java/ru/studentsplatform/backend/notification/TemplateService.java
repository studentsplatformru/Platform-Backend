package ru.studentsplatform.backend.notification;

import ru.studentsplatform.backend.entities.model.enums.NotificationType;
import ru.studentsplatform.backend.notification.enumerated.MessageType;

/**
 * Обрабатывает аргументы и по типу сообщения и
 * возвращает необходимый шаблон для отправки по почте.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
 */
public interface TemplateService {

    /**
     * Обрабатывает аргументы и по типу сообщения и возвращает необходимый шаблон.
     *
     * @param type Получает {@link MessageType} для определения типа шаблона.
     * @param args Получает на вход необходимые данные для замены в шаблоне.
     * @param notificationType тип уведомления в виде {@link NotificationType}.
     * @return Сообщение для отправки через почту.
     *
     * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
     */
    String getTemplate(MessageType type, NotificationType notificationType, Object... args);

}

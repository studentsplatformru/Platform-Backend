package ru.studentsplatform.backend.notification;

import ru.studentsplatform.backend.notification.enumerated.MessageType;

/**
 * Обрабатывает аргументы и по типу сообщения и
 * возвращает необходимый шаблон в виде html
 * необходимый для отправки по почте.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
 */
public interface EmailTemplateService {

    /**
     * Обрабатывает аргументы и по типу сообщения и
     * возвращает необходимый шаблон.
     *
     * @param type
     *        Получает {@link MessageType} для отпределения типа шаблона
     * @param args
     *        Получает на вход необходимые данные для замены в шаблоне
     *
     * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
     */
    String getEmailTemplate(MessageType type, String ...args);

}

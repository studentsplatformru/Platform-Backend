package ru.studentsplatform.backend.notification;

import ru.studentsplatform.backend.notification.enumerated.MessageType;

/**
 * Интерфейс для обработки шаблонов с определённой логикой.
 * Помещается в необходимый {@link MessageType}
 *
 * @author Danila K (karnacevich5323537@gmail.com) (20.08.2020).
 */
public interface Template {

    /**
     * Метод для обработки и выдачи html сообщений.
     * @param args аргументы для обработки сообщения.
     * Зависят от конкретного типа.
     * @return готовое для отправки html сообщение.
     */
    String getHtmlTemplate(Object... args);

    /**
     * Метод для обработки и выдачи коротких(бот) сообщений.
     * @param args аргументы для обработки сообщения.
     * Зависят от конкретного типа.
     * @return готовое для отправки сообщение.
     */
    String getBotTemplate(Object... args);

}

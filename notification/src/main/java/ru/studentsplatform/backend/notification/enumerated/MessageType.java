package ru.studentsplatform.backend.notification.enumerated;

import ru.studentsplatform.backend.notification.Template;
import ru.studentsplatform.backend.notification.templates.CustomTemplate;
import ru.studentsplatform.backend.notification.templates.EventsDayTemplate;
import ru.studentsplatform.backend.notification.templates.StandardTemplate;

/**
 * Enum для описания типа сообщения.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
 */
public enum MessageType {
    //    TASK_ASSIGMENT,
    //    REGISTRATION,
    //    WELCOME,
    /**
     * Параметры для вставки:
     * 1-й : Список занятий в виде {@code List<SpbuEvent>}
     */
    EVENTS_DAY_NOTIFICATION(
            1, new EventsDayTemplate(
            "notification/src/main/resources/templates/events_day_notification.html",
            ""
    )),
    /**
     * Параметры для вставки:
     * 1-й : Предмет получения оценки.
     * 2-й : Оценка по предмету.
     */
    MARK_NOTIFICATION(
            2, new StandardTemplate(
            "notification/src/main/resources/templates/mark_notification.html",
            "У Вас новая оценка!\n %s : %s"
    )),

    /**
     * Параметры для вставки:
     * 1-й : Ссылка на подтверждение email.
     */
    EMAIL_CONFIRMATION(
            1, new StandardTemplate(
            "notification/src/main/resources/templates/email_confirmation.html",
            "Пожалуйста, подтвердите свой email:\n %s"
    )),

    /**
     * Параметры для вставки:
     * 1-й : Свободное сообщение передаётся в качестве аргумента.
     */
    CUSTOM(1, new CustomTemplate("-", "-"));

    private final int parameterCount;
    private final Template template;

    /**
     //     * @param path               Путь к шаблону.
     * @param parameterCount     Количество вставляемых параметров.
    //     * @param botPattern         шаблон в формате строки для сообщений через бота.
     */
    MessageType(int parameterCount, Template template) {
        this.parameterCount = parameterCount;
        this.template = template;
    }

    public int getParameterCount() {
        return parameterCount;
    }

    public Template getTemplateClass() {
        return template;
    }
}

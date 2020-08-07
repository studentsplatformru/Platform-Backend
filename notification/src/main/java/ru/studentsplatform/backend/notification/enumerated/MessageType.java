package ru.studentsplatform.backend.notification.enumerated;

import ru.studentsplatform.backend.notification.MessageTypeInterface;

/**
 * Реализация {@link MessageTypeInterface}.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
 */
public enum MessageType implements MessageTypeInterface {
    //    TASK_ASSIGMENT,
    //    REGISTRATION,
    //    WELCOME,
    /**
     * Параметры:
     * 1-й : Предмет получения оценки
     * 2-й : Оценка по предмету
     */
    MARK_NOTIFICATION("notification\\src\\main\\resources\\templates\\mark_notification.html", 2),

    /**
     * Параметры:
     * 1-й : Ссылка на подтверждение email
     */
    EMAIL_CONFIRMATION("notification\\src\\main\\resources\\templates\\email_confirmation.html", 1);

    private final String path;
    private final int parameterCount;

    /**
     * @param path               Путь к шаблону
     * @param parameterCount     Количество вставляемых параметров
     */
    MessageType(String path, int parameterCount) {
        this.path = path;
        this.parameterCount = parameterCount;
    }

    public String getPath() {
        return path;
    }

    public int getParameterCount() {
        return parameterCount;
    }
}

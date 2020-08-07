package ru.studentsplatform.backend.notification.enumerated;

import ru.studentsplatform.backend.notification.MessageTypeInterface;

/**
 * Реализация {@link MessageTypeInterface}.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
 */
public enum MessageType implements MessageTypeInterface {
//    REGISTRATION,
//    MARK_NOTIFICATION,
//    TASK_ASSIGMENT,
    // продумать комменты к каждому шаблону
    EMAIL_CONFIRMATION("notification\\src\\main\\resources\\templates\\EMAIL_CONFIRMATION.html", 1);

    private final String path;
    private final int parameterCount;

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

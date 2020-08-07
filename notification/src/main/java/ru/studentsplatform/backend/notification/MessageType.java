package ru.studentsplatform.backend.notification;

public enum MessageType {
//    REGISTRATION,
//    MARK_NOTIFICATION,
//    TASK_ASSIGMENT,
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

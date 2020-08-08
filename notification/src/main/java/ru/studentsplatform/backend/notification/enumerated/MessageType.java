package ru.studentsplatform.backend.notification.enumerated;

/**
 * Enum для описания типа сообщения.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (07.08.2020).
 */
public enum MessageType{
    //    TASK_ASSIGMENT,
    //    REGISTRATION,
    //    WELCOME,
    /**
     * Параметры:
     * 1-й : Предмет получения оценки
     * 2-й : Оценка по предмету
     */
    MARK_NOTIFICATION(
            "notification\\src\\main\\resources\\templates\\mark_notification.html",
            2,
            "У Вас новая оценка!\n %s : %s"),
    /**
     * Параметры:
     * 1-й : Ссылка на подтверждение email
     */
    EMAIL_CONFIRMATION(
            "notification\\src\\main\\resources\\templates\\email_confirmation.html",
            1,
            "Пожалуйства, поддтвердите свой email:\n %s");

    private final String path;
    private final String botPattern;
    private final int parameterCount;

    /**
     * @param path               Путь к шаблону
     * @param parameterCount     Количество вставляемых параметров
     */
    MessageType(String path, int parameterCount, String botPattern) {
        this.path = path;
        this.parameterCount = parameterCount;
        this.botPattern = botPattern;
    }

    public String getPath() {
        return path;
    }

    public int getParameterCount() {
        return parameterCount;
    }

    public String getBotPattern() {
        return botPattern;
    }
}

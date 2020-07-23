package ru.studentsplatform.backend.service.exception;

import org.springframework.http.HttpStatus;
import ru.studentsplatform.backend.service.exception.core.BusinessExceptionReason;

/**
 * Реализация {@link BusinessExceptionReason}.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (10.07.2020). feat. Krylov Sergey (krylov.sergey.1999@yandex.ru)
 */
public enum ServiceExceptionReason implements BusinessExceptionReason {

    UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "UNEXPECTED_ERROR", "Непредвиденная ошибка"),

    // Ошибки целлостности данных
    NOTE_NOT_FOUND(HttpStatus.NOT_FOUND, "F001", "Запись с id %s не найдена"),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "F002", "Комментарий с id %s не найден");

    private final HttpStatus status;
    private final String code;
    private final String messagePattern;

    /**
     * @param status         Статус ошибки
     * @param code           Код ошибки
     * @param messagePattern Паттерн для формирования текста ошибки
     */
    ServiceExceptionReason(HttpStatus status, String code, String messagePattern) {
        this.status = status;
        this.code = code;
        this.messagePattern = messagePattern;
    }

    public String getCode() {
        return code;
    }

    public String getMessagePattern() {
        return messagePattern;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
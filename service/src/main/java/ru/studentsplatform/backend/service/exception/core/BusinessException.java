package ru.studentsplatform.backend.service.exception.core;

import org.springframework.http.HttpStatus;

import java.util.Objects;

/**
 * Исключения для бизнес ошибок.
 * @author Danila K (karnacevich5323537@gmail.com) (10.07.2020).
 */
public class BusinessException extends RuntimeException {

    /**
     * Причина возникновения исключения.
     */
    private final BusinessExceptionReason reason;

    /**
     * Параметры исключения.
     */
    private final Object[] parameters;

    public BusinessException(BusinessExceptionReason reason, Object... parameters) {
        this.reason = reason;
        this.parameters = parameters;
    }

    public BusinessExceptionReason getReason() {
        return reason;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public String getCode() {
        return reason.getCode();
    }

    public HttpStatus getStatus() {
        return reason.getStatus();
    }

    /**
     * Проверяем есть ли параметры, если нет, то возвращаем базовый текст
     * иначе вставляем параметры.
     *
     * @return текст ошибки
     */
    @Override
    public String getMessage() {
        return Objects.isNull(parameters)
                ? reason.getMessagePattern()
                : String.format(reason.getMessagePattern(), parameters);
    }

}

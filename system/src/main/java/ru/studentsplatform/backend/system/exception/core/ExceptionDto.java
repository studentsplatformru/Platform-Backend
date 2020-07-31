package ru.studentsplatform.backend.system.exception.core;

import org.springframework.http.HttpStatus;

/**
 * Сущность для отправки пользователю в случае ошибки.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (30.07.2020).
 */
public class ExceptionDto {

    /**
     * Сообщение для пользователя.
     */
    private String message;

    /**
     * Статус ошибки.
     */
    private HttpStatus status;

    public ExceptionDto(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}

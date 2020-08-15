package ru.studentsplatform.backend.system.exception.domain;

import org.springframework.http.HttpStatus;

/**
 * Сущность для отправки пользователю в случае ошибки.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (30.07.2020).
 */
public class ExceptionDTO {

	/**
	 * Сообщение для пользователя.
	 */
	private String message;

	/**
	 * Сообщение для пользователя.
	 */
	private String className;

	/**
	 * Статус ошибки.
	 */
	private HttpStatus status;

	public ExceptionDTO(String message, HttpStatus status, String className) {
		this.message = message;
		this.status = status;
		this.className = className;
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}

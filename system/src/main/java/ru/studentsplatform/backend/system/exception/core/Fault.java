package ru.studentsplatform.backend.system.exception.core;

import org.springframework.http.HttpStatus;

/**
 * Глобальное исключение внутри проекта.
 *
 * @author Krylov Sergey (15.08.2020)
 */
public class Fault extends Exception {
	private final HttpStatus status;

	public Fault(HttpStatus status) {
		this.status = status;
	}

	public Fault(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public Fault(String message, Throwable cause, HttpStatus status) {
		super(message, cause);
		this.status = status;
	}

	public Fault(Throwable cause, HttpStatus status) {
		super(cause);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}
}

package ru.studentsplatform.backend.system.exception.core;

/**
 * Непредвиденное исключение.
 * <p>
 * Используется для индикации ситуаций, которые не должны возникать при штатной работе методов
 * с корректными входными параметрами и консистентными данными в БД.
 * </p>
 *
 * @author Krylov Sergey (15.08.2020)
 */
public class UnexpectedException extends RuntimeException {

	public UnexpectedException(String message) {
		super(message);
	}

	public UnexpectedException(String message, Throwable cause) {
		super(message, cause);
	}
}
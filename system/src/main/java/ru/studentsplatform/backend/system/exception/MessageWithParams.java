package ru.studentsplatform.backend.system.exception;

/**
 * Шаблон сообщения об ошибке.
 *
 * @author Krylov Sergey (15.08.2020)
 */
public interface MessageWithParams {
	/**
	 * Возвращает шаблон сообщения об ошибке.
	 *
	 * @return Шаблон сообщения об ошибке.
	 */
	String getMessage();
}

package ru.studentsplatform.backend.system.exception;

import org.springframework.http.HttpStatus;

/**
 * Интерфейс для описания причины возникновения ошибки.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (23.07.2020).
 */
public interface BusinessExceptionReason {

	/**
	 * Код ошибки.
	 *
	 * @return код ошибки
	 */
	String getCode();

	/**
	 * Паттерн для формирования сообщения ошибки.
	 * Подстановка текста осуществляется через замену '%s' на нужный текст.
	 *
	 * @return паттерн для формирования сообщения ошибки
	 */
	MessageWithParams getMessageWithParams();

	/**
	 * Возвращает статус ошибки исключения.
	 *
	 * @return статус ошибки
	 */
	HttpStatus getStatus();
}

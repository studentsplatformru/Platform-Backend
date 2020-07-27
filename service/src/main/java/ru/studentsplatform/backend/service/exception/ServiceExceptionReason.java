package ru.studentsplatform.backend.service.exception;

import org.springframework.http.HttpStatus;
import ru.studentsplatform.backend.service.exception.core.BusinessExceptionReason;

/**
 * Реализация {@link BusinessExceptionReason}.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (23.07.2020). feat. Krylov Sergey (krylov.sergey.1999@yandex.ru)
 */
public enum ServiceExceptionReason implements BusinessExceptionReason {

	UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "UNEXPECTED_ERROR", "Непредвиденная ошибка"),

	// Ошибки целлостности данных
	NOTE_NOT_FOUND(HttpStatus.NOT_FOUND, "F001", "Запись с id %s не найдена"),
	COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "F002", "Комментарий с id %s не найден"),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "F003", "Пользователь с id %s не найден"),
	SCHEDULE_CELL_NOT_FOUND(HttpStatus.NOT_FOUND, "F004", "Отметка в расписании с id %s не найдена"),
	NO_UPLOADED_FILES_FOUND(HttpStatus.NOT_FOUND, "F005", "Не найдено ни одного загружаемого файла к задаче с ID %d"),
	FILE_INDEX_NOT_EXIST(HttpStatus.NOT_FOUND, "F006", "Файла с таким индексом не существует в данной задаче");

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
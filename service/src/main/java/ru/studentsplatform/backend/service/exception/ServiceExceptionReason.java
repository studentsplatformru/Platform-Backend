package ru.studentsplatform.backend.service.exception;

import org.springframework.http.HttpStatus;
import ru.studentsplatform.backend.system.exception.BusinessExceptionReason;
import ru.studentsplatform.backend.system.exception.MessageWithParams;

/**
 * Реализация {@link BusinessExceptionReason}.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (23.07.2020). feat. Krylov Sergey (krylov.sergey.1999@yandex.ru)
 */
public enum ServiceExceptionReason implements BusinessExceptionReason {
	// Бизнес-ошибки - E
	// Информационные ошибки - I

	// Ошибки целостности данных
	NOTE_NOT_FOUND(HttpStatus.NOT_FOUND, "D001", ErrorMessageWithParams.D001),
	COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "D002", ErrorMessageWithParams.D002),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "D003", ErrorMessageWithParams.D003),
	SCHEDULE_CELL_NOT_FOUND(HttpStatus.NOT_FOUND, "D004", ErrorMessageWithParams.D004),
	NO_UPLOADED_FILES_FOUND(HttpStatus.NOT_FOUND, "D005", ErrorMessageWithParams.D005),
	FILE_INDEX_NOT_EXIST(HttpStatus.NOT_FOUND, "D006", ErrorMessageWithParams.D006),
	NULL_FILE_EXCEPTION(HttpStatus.BAD_REQUEST, "D007", ErrorMessageWithParams.D007),
	USER_INFO_ALREADY_EXISTS(HttpStatus.CONFLICT, "D008", ErrorMessageWithParams.D008),
	NULL_IMAGE_FILE(HttpStatus.BAD_REQUEST, "D009", ErrorMessageWithParams.D009),
	USER_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "D010", ErrorMessageWithParams.D010),
	FACULTY_NOT_FOUND(HttpStatus.NOT_FOUND, "D011", ErrorMessageWithParams.D011),
	DIRECTION_NOT_FOUND(HttpStatus.NOT_FOUND, "D012", ErrorMessageWithParams.D012),
	DEPARTMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "D013", ErrorMessageWithParams.D013),
	TEAM_NOT_FOUND(HttpStatus.NOT_FOUND, "D014", ErrorMessageWithParams.D014),
	USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "D015", ErrorMessageWithParams.D015),
	SUBJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "D016", ErrorMessageWithParams.D016),
	PLACE_STUDY_NOT_FOUND(HttpStatus.NOT_FOUND, "D017", ErrorMessageWithParams.D017),
	DISCIPLINE_NOT_FOUND(HttpStatus.NOT_FOUND, "D018", ErrorMessageWithParams.D018),
	UNIVERSITY_NOT_FOUND(HttpStatus.NOT_FOUND, "D019", ErrorMessageWithParams.D019),
	SCHEDULE_USER_CELL_NOT_FOUND(HttpStatus.NOT_FOUND, "D020", ErrorMessageWithParams.D020);

	private final HttpStatus status;
	private final String code;
	private final MessageWithParams messageWithParams;

	/**
	 * @param status            Статус ошибки
	 * @param code              Код ошибки
	 * @param messageWithParams Паттерн для формирования текста ошибки
	 */
	ServiceExceptionReason(HttpStatus status,
						   String code,
						   MessageWithParams messageWithParams) {
		this.status = status;
		this.code = code;
		this.messageWithParams = messageWithParams;
	}

	public String getCode() {
		return code;
	}

	public MessageWithParams getMessageWithParams() {
		return messageWithParams;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
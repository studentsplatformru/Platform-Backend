package ru.studentsplatform.backend.service.exception;

import org.springframework.http.HttpStatus;
import ru.studentsplatform.backend.system.exception.core.BusinessExceptionReason;

/**
 * Реализация {@link BusinessExceptionReason}.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (23.07.2020). feat. Krylov Sergey (krylov.sergey.1999@yandex.ru)
 */
public enum ServiceExceptionReason implements BusinessExceptionReason {

	UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "UNEXPECTED_ERROR", "Непредвиденная ошибка"),

	// Ошибки целлостности данных
	NOTE_NOT_FOUND(HttpStatus.NOT_FOUND, "F001", "Запись с Id %d не найдена"),
	COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "F002", "Комментарий с Id %d не найден"),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "F003", "Пользователь с Id %d не найден"),
	SCHEDULE_CELL_NOT_FOUND(HttpStatus.NOT_FOUND, "F004",
			"Отметка в расписании, связанная с задачей с Id %d, не найдена"),
	NO_UPLOADED_FILES_FOUND(HttpStatus.NOT_FOUND, "F005",
			"Не найдено ни одного загружаемого файла к задаче с Id %d"),
	FILE_INDEX_NOT_EXIST(HttpStatus.NOT_FOUND, "F006",
			"Файла с ID %d не существует в задаче с Id %d"),
	NULL_FILE_EXCEPTION(HttpStatus.BAD_REQUEST, "F007",
			"Отсутствет содержимое загружаемого в задачу c Id %d файла"),
	USER_INFO_ALREADY_EXISTS(HttpStatus.CONFLICT, "F008",
			"Информация о пользователе с id %d уже существует"),
	NULL_IMAGE_FILE(HttpStatus.BAD_REQUEST, "F009",
			"Файл изображения пуст"),
	USER_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "F010", "Информация о пользователе с Id %d не найдена"),
	FACULTY_NOT_FOUND(HttpStatus.NOT_FOUND, "F011", "Информация об институте с Id %d не найдена"),
	DIRECTION_NOT_FOUND(HttpStatus.NOT_FOUND, "F012", "Информация о направлении с Id %d не найдена"),
	DEPARTMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "F013", "Информация о кафедре с Id %d не найдена"),
	TEAM_NOT_FOUND(HttpStatus.NOT_FOUND, "F014", "Информация о группе с Id %d не найдена"),
	USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "F015",
			"Пользователь с id %d уже существует"),
	SUBJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "F016",
			"Информация о предмете с Id %d не найдена"),
	PLACE_STUDY_NOT_FOUND(HttpStatus.NOT_FOUND, "F017",
			"Информация о месте проведения занятий с Id %d не найдена"),
	DISCIPLINE_NOT_FOUND(HttpStatus.NOT_FOUND, "F018",
			"Информация о дисциплине с Id %d не найдена"),
	UNIVERSITY_NOT_FOUND(HttpStatus.NOT_FOUND, "F022",
			"Информация об университете с Id %d не найдена");


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
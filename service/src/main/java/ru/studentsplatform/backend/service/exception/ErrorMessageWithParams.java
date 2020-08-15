package ru.studentsplatform.backend.service.exception;

import ru.studentsplatform.backend.system.exception.MessageWithParams;

/**
 * Шаблоны сообщений об ошибках.
 *
 * @author Krylov Sergey (15.08.2020)
 */
public enum ErrorMessageWithParams implements MessageWithParams {
	D001("Запись с id %d, не найдена"),
	D002("Комментарий с id %d, не найден"),
	D003("Пользователь с id %d, не найден"),
	D004("Ячейка расписания с id %d, не найдена"),
	D005("Не найдено ни одного загружаемого файла к задаче с id %d"),
	D006("Файла с id %d не существует в задаче с id %d"),
	D007("Отсутствует содержимое загружаемого в задачу c id %d файла"),
	D008("Информация о пользователе с id %d уже существует"),
	D009("Файл изображения пуст"),
	D010("Информация о пользователе с id %d не найдена"),
	D011("Информация об институте с id %d не найдена"),
	D012("Информация о направлении с Id %d не найдена"),
	D013("Информация о кафедре с id %d не найдена"),
	D014("Информация о группе с id %d не найдена"),
	D015("Пользователь с id %d уже существует"),
	D016("Информация о предмете с id %d не найдена"),
	D017("Информация о месте проведения занятий с id %d не найдена"),
	D018("Информация о дисциплине с id %d не найдена"),
	D019("Информация об университете с id %d не найдена"),
	D020("Ячейка расписания с id %d пользователя  не найдена");

	private final String message;

	ErrorMessageWithParams(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}

package ru.studentsplatform.backend.domain.pojo.filters;

import lombok.Data;

import java.time.OffsetDateTime;

/**
 * Сущность, содержащая фильтры для поиска записей Task,
 * фильтры задаются в requestBody с названиями, соответствующими названиям полей.
 * Поля, содержащие NULL, будут игнорироваться при поиске.
 */
@Data
public class TaskFilterPOJO {
	//Поиск с учетом временных рамок (начало)
	private OffsetDateTime startTime;
	//Поиск с учетом временных рамок (конец)
	private OffsetDateTime endTime;
	//Поиск с учетом Id предмета
	private Long subjectId;
	//Поиск с учетом Id группы студентов
	private Long groupId;
	//Поиск с учетом Id ячейки расписания пользователя
	private Long userCellId;
	//Поиск с учетом семестра
	private Integer semester;
	//Поиск с учетом Id пользователя
	private Long userId;
}

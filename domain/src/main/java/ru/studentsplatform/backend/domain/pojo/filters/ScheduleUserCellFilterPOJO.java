package ru.studentsplatform.backend.domain.pojo.filters;

import lombok.Data;

import java.time.OffsetDateTime;

/**
 * Сущность, содержащая фильтры для поиска записей ScheduleUserCell,
 * фильтры задаются в requestBody с названиями, соответствующими названиям полей.
 * Поля, содержащие NULL, будут игнорироваться при поиске.
 */
@Data
public class ScheduleUserCellFilterPOJO {
	//Поиск с учетом Id предмета
	private Long subjectId;
	//Поиск с учетом Id дисциплины
	private Long disciplineId;
	//Поиск с учетом Id пользователя
	private Long userId;
	//Поиск с учетом ячейки расписания
	private Long scheduleCellId;
	//Поиск с учетом семестра
	private Integer semester;
	//Поиск с учетом временных рамок (начало)
	private OffsetDateTime startTime;
	//Поиск с учетом временных рамок (конец)
	private OffsetDateTime endTime;
	//Поиск по присутствию/отсутствию
	private Boolean presence;
}

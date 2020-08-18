package ru.studentsplatform.backend.university.schedule.spbu.service;

import ru.studentsplatform.backend.entities.model.spbu.SpbuEvent;

import java.util.List;

/**
 * Сервис для работы с расписанием СПБГУ.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 16.08.2020
 */
public interface SpbuEventService {

	/**
	 * Сохраняет занятие в БД.
	 * @param entity занятие студенческой группы
	 * @return Сохраненная сущность занятия
	 */
	SpbuEvent create(SpbuEvent entity);

	/**
	 * Возвращает всё сохраненное расписание для конкретной группы.
	 * @param groupName имя студенческой группы
	 * @return Сущность расписания.
	 */
	List<SpbuEvent> getByGroupName(String groupName);

	/**
	 * Возвращает кэшированное расписание, если на этой неделе уже были запросы к конкретной группе.
	 * В противном случае обращается к БД СПБГУ.
	 * @param teamName имя студенческой группы
	 * @return список дней с расписанием
	 */
	List<SpbuEvent> getWeekEvents(String teamName) throws feign.RetryableException;

}

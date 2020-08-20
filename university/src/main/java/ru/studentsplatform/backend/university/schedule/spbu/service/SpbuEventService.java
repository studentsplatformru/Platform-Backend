package ru.studentsplatform.backend.university.schedule.spbu.service;

import ru.studentsplatform.backend.entities.model.spbu.SpbuEvent;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис для работы с расписанием СПБГУ.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 16.08.2020
 */
public interface SpbuEventService {

	/**
	 * Сохраняет занятие в БД.
	 * @param entity Занятие студенческой группы
	 * @return		 Сохраненная сущность занятия
	 */
	SpbuEvent create(SpbuEvent entity);

	/**
	 * Возвращает всё сохраненное расписание для конкретной группы.
	 * @param groupName	 Имя студенческой группы
	 * @return			 Сущность расписания.
	 */
	List<SpbuEvent> getByGroupName(String groupName);

	/**
	 * Позволяет найти расписание на текущий день.
	 * @param teamName	 Имя студенческой группы СПБГУ
	 * @param day		 День, для которого будет произведен поиск расписания
	 * @return			 Список занятий на выбранный день
	 */
	List<SpbuEvent> getEventsByDay(String teamName, LocalDate day);

	/**
	 * Возвращает кэшированное расписание, если на этой неделе уже были запросы к конкретной группе.
	 * В противном случае обращается к БД СПБГУ.
	 * @param teamName	 Имя студенческой группы
	 * @return			 Список дней с расписанием на текущую неделю
	 */
	List<SpbuEvent> getEventsByCurrentWeek(String teamName);

	/**
	 * Возвращает расписание в выбранных временных рамках.
	 * @param teamName	 Имя студенческой группы
	 * @param startTime	 Начало временных рамок
	 * @param endTime	 Конец временных рамок
	 * @return			 Список занятий, найденных в выбранных временных рамках
	 */
	List<SpbuEvent> getEventsByInterval(String teamName, String startTime, String endTime);

}

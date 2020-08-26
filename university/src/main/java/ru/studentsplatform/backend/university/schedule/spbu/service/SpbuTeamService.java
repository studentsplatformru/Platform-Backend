package ru.studentsplatform.backend.university.schedule.spbu.service;

import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;

import java.util.List;

/**
 * CRUD сервис SpbuTeam.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 16.08.2020
 */
public interface SpbuTeamService {

	/**
	 * Сохраняет студенческую группу в БД.
	 *
	 * @param team Студенческая группа
	 * @return Созданная группа
	 */
	SpbuTeam create(SpbuTeam team);

	/**
	 * Возвращает список всех групп СПБГУ.
	 * @return все группы СПБГУ
	 */
	List<SpbuTeam> getAll();

	/**
	 * Находит SpbuTeam по её имени.
	 *
	 * @param name Имя студенческой группы СПБГУ
	 * @return Объект студенческой группы спбгу
	 */
	SpbuTeam getByName(String name);

	/**
	 * Сохраняет группы для определенного направления.
	 *
	 * @param alias сокращённое наименование направления
	 */
	void saveAllAliasGroups(String alias);

	/**
	 * Сохраняет группы для каждого направления.
	 */
	void saveAllGroups();

}

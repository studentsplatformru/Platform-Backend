package ru.studentsplatform.backend.university.schedule.spbu.service;

import ru.studentsplatform.backend.domain.dto.spbu.SpbuEventDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuProgramLevelDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuScheduleDayDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuStudyProgramDTO;
import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;

import java.util.List;

public interface SpbuService {

	/**
	 * Достаёт программы обучения из класса-оболочки.
	 * @param programLevelDTO уровни программ обучения
	 * @return программы обучения
	 */
	List<SpbuStudyProgramDTO> studyProgramUnwrap(List<SpbuProgramLevelDTO> programLevelDTO);

	/**
	 * Достает занятия из объекта дня.
	 * @param days список дней
	 * @return список занятий
	 */
	List<SpbuEventDTO> eventUnwrap(List<SpbuScheduleDayDTO> days);

	/**
	 * Сохраняет студенческую группу в БД.
	 * @param team Студенческая группа
	 * @return Созданная группа
	 */
	SpbuTeam create(SpbuTeam team);

	/**
	 * Находит SpbuTeam по её имени.
	 * @param name Имя студенческой группы СПБГУ
	 * @return Объект студенческой группы спбгу
	 */
	SpbuTeam getByName(String name);

	/**
	 * Запускает поток сохранения сех групп для определенного направления.
	 * @param alias сокращённое наименование направления
	 */
	void saveAllAliasGroups(String alias);
}

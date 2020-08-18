package ru.studentsplatform.backend.university.schedule.spbu.service;

import ru.studentsplatform.backend.domain.dto.spbu.SpbuEventDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuProgramLevelDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuScheduleDayDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuStudyProgramDTO;

import java.util.List;

/**
 * Сервис для разворачивания объектов-оберток, полученных из БД СПБГУ.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 16.08.2020
 */
public interface SpbuUnwrapService {

	/**
	 * Достаёт программы обучения из класса-оболочки.
	 *
	 * @param programLevelDTO уровни программ обучения
	 * @return программы обучения
	 */
	List<SpbuStudyProgramDTO> studyProgramUnwrap(List<SpbuProgramLevelDTO> programLevelDTO);

	/**
	 * Достает занятия из объекта дня.
	 *
	 * @param days список дней
	 * @return список занятий
	 */
	List<SpbuEventDTO> eventUnwrap(List<SpbuScheduleDayDTO> days);

}

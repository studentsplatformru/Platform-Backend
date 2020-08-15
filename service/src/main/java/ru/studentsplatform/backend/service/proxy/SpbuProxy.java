package ru.studentsplatform.backend.service.proxy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuDivisionDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuGroupWrapperDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuProgramLevelDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuScheduleDayWrapperDTO;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.List;

/**
 * Класс посылает запросы к api расписания СПбГУ.
 *
 * @author Archive-Vian (sas-artamonov@yandex.ru) 10.08.2020
 */
@Profiled
public interface SpbuProxy {

	/**
	 * Получение всех направлений подготовки.
	 * @return Список DTO програм обучения
	 */
	@GetMapping("study/divisions/")
	List<SpbuDivisionDTO> getDivisions();

	/**
	 * Получение програм обучения для направления.
	 * @param alias Сокращённое наименование програм обучения
	 * @return Список уровней програм Обучения (бакалавр, магистратуа, и.т.д)
	 */
	@GetMapping(value = "study/divisions/{alias}/programs/levels")
	List<SpbuProgramLevelDTO> getProgramLevels(@PathVariable(name = "alias") String alias);

	/**
	 * Получение списка групп для конкретной программы обучения.
	 * @param id Id Программы обучения
	 * @return Объект, содержащий список програм обучения
	 */
	@GetMapping("progams/{id}/groups")
	SpbuGroupWrapperDTO getGroups(@PathVariable(name = "id") String id);

	/**
	 * Получение занятий для оределенной группы на следующую неделю.
	 * @param id Id студенческой группы
	 * @return Объект, содержащий список дней с занятиями
	 */
	@GetMapping("groups/{id}/events")
	SpbuScheduleDayWrapperDTO getDays(@PathVariable(name = "id") String id);

	/**
	 * Получение занятий для оределенной группы за определенный период.
	 * @param id Id студенческой группы
	 * @param startTime Начало перида
	 * @param endTime Конец периода
	 * @return Объект, содержащий список дней с занятиями
	 */
	@GetMapping("groups/{id}/events/{start}/{end}")
	SpbuScheduleDayWrapperDTO getDays(@PathVariable(name = "id")String id,
									  @PathVariable(name = "start") String startTime,
									  @PathVariable(name = "end") String endTime);
}

package ru.studentsplatform.backend.endpoint.rest.spbu;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuDivisionDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuEventDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuStudyProgramDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuTeamDTO;
import ru.studentsplatform.backend.system.exception.core.Fault;

import java.time.LocalDate;
import java.util.List;

/**
 * Интерфейс контроллера, связанного с получением данных о группах и занятиях СПБГУ.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 10.08.2020
 */
public interface SpbuDataController {

	/**
	 * Получение списка направлений подготовки.
	 *
	 * @return список DTO направлений подготовки
	 */
	@GetMapping("/divisions")
	ResponseEntity<List<SpbuDivisionDTO>> getDivisions();

	/**
	 * Получение программ обучения.
	 *
	 * @param alias Сокращённое наименование направления подготовки
	 * @return 		Список DTO направлений подготовки
	 */
	@GetMapping("/division/{alias}/programs")
	ResponseEntity<List<SpbuStudyProgramDTO>> getStudyPrograms(@PathVariable(name = "alias") String alias);

	/**
	 * Возвращает список студенческих групп для выбраного Id прграммы обучения.
	 * @param programId Id программы обучения
	 * @return 			Список студенческих групп СПБГУ для выбаной программы обучения
	 */
	@GetMapping("program/{id}/groups")
	ResponseEntity<List<SpbuTeamDTO>> getGroups(@PathVariable(name = "id") String programId);

	/**
	 * Возвращает список занятий на текущую неделю для выбраной группы.
	 * @param groupName Имя студенческой группы
	 * @return 			Список заний на следующую неделю
	 * @throws Fault 	Непредвиденная ошибка
	 */
	@GetMapping("group/{groupName}/events")
	ResponseEntity<List<SpbuEventDTO>> getEventsForWeek(@PathVariable(name = "groupName") String groupName)
			throws Fault;

	/**
	 * Возвращает список занятий выбраный день.
	 * @param groupName Имя студенческой группы СПБГУ
	 * @param day 		Выбраный день в формате dd-MM-yyyy
	 * @return 			Список занятий на выбраный день
	 */
	@GetMapping("group/{groupName}/events/{day}")
	ResponseEntity<List<SpbuEventDTO>> getEventsByDay(@PathVariable(name = "groupName") String groupName,
														   @PathVariable(name = "day")
													  @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate day);

	/**
	 * Получение списка занятий за определенный период по имени группы.
	 *
	 * @param name      Имя студенческой группы
	 * @param startTime Начало периода
	 * @param endTime   Конец периода
	 * @return 			Список занятий
	 */
	@GetMapping("group/{name}/events/{start}/{end}")
	ResponseEntity<List<SpbuEventDTO>> getEventsByInterval(@PathVariable(name = "name") String name,
																@PathVariable(name = "start") String startTime,
																@PathVariable(name = "end") String endTime);

	/**
	 * Сохраняет в БД все группы для выбранного направления.
	 *
	 * @param alias Сокращённое наименование подготовки
	 * @return 		Ответ с кодом 200 и сообщением о результате сохранения
	 */
	@GetMapping("division/{alias}/saveAllGroups")
	ResponseEntity<String> saveAllGroupsForAlias(@PathVariable(name = "alias") String alias);

	@GetMapping("saveAllGroups")
	ResponseEntity<String> saveAllGroups();

	@GetMapping("stressTest/{iterations}")
	ResponseEntity<Boolean> stressTest(@PathVariable(name = "iterations") int iterations);
}

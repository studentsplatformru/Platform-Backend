package ru.studentsplatform.backend.endpoint.rest.spbu;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuDivisionDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuEventDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuStudyProgramDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuTeamDTO;
import ru.studentsplatform.backend.entities.model.spbu.SpbuEvent;
import ru.studentsplatform.backend.system.exception.core.Fault;

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
	 * @param alias сокращённое наименование направления подготовки
	 * @return список DTO направлений подготовки
	 */
	@GetMapping("/division/{alias}/programs")
	ResponseEntity<List<SpbuStudyProgramDTO>> getStudyPrograms(@PathVariable(name = "alias") String alias);

	/**
	 * Получение студенческих групп.
	 *
	 * @param id Id программы обучения
	 * @return список студенческих групп
	 */
	@GetMapping("program/{id}/groups")
	ResponseEntity<List<SpbuTeamDTO>> getGroups(@PathVariable(name = "id") String id);

	/**
	 * Получение списка занятий за определенный период по Id группы.
	 *
	 * @param id        Id студенческой группы
	 * @param startTime начало периода
	 * @param endTime   конец периода
	 * @return список занятий
	 */
	@GetMapping("group/{id}/events/{start}/{end}")
	ResponseEntity<List<SpbuEventDTO>> getEventsByIdForTimeInterval(@PathVariable(name = "id") String id,
																	@PathVariable(name = "start") String startTime,
																	@PathVariable(name = "end") String endTime);

	/**
	 * Получение списка занятий за определенный период по имени группы.
	 *
	 * @param name      имя студенческой группы
	 * @param startTime начало периода
	 * @param endTime   конец периода
	 * @return список занятий
	 */
	@GetMapping("group/name/{name}/events/{start}/{end}")
	ResponseEntity<List<SpbuEventDTO>> getEventsByNameForTimeInterval(@PathVariable(name = "name") String name,
																	  @PathVariable(name = "start") String startTime,
																	  @PathVariable(name = "end") String endTime);

	/**
	 * Сохраняет в БД все группы для выбранного направления.
	 *
	 * @param alias Сокращённое наименование подготовки
	 * @return 		Ответ с кодом 200 и сообщением о результате сохранения
	 */
	@GetMapping("division/{alias}/saveAllGroups")
	ResponseEntity<String> saveAllGroupsToDB(@PathVariable(name = "alias") String alias);

	/**
	 * Возвращает список занятий на следующую неделю для выбраной группы.
	 * @param groupName имя студенческой группы
	 * @return список заний на следующую неделю
	 * @throws Fault непредвиденная ошибка
	 */
	@GetMapping("getEvents/{groupName}")
	ResponseEntity<List<SpbuEvent>> getNextWeekEvents(@PathVariable(name = "groupName") String groupName) throws Fault;

	/**
	 * Обновляет кэш для конкретной группы и возвращает обновленные данные.
	 * @param groupName Имя студенческой группы СПБГУ
	 * @return Ответ с кодом 200 и списком обновленных данных о расписании группы на неделю
	 * @throws Fault непредвиденная ошибка
	 */
	@GetMapping("refreshEvents/{groupName}")
	ResponseEntity<List<SpbuEvent>> refreshEvents(@PathVariable(name = "groupName") String groupName) throws Fault;

}

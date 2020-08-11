package ru.studentsplatform.backend.endpoint.rest.spbu;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuDivisionDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuEventDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuStudyProgramDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuTeamDTO;

import java.util.List;

/**
 * Интерфейс контроллера, связанного с получением данных о группах и занятиях СПБГУ.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 10.08.2020
 */
public interface SpbuDataController {

	/**
	 * Получение списка направлений поготовки.
	 * @return список DTO направлений подготовки
	 */
	@GetMapping("/divisions")
	List<SpbuDivisionDTO> getDivisions();

	/**
	 * Получение программ обучения.
	 * @param alias сокращённое наименование направления подготовки
	 * @return		список DTO направлений подготовки
	 */
	@GetMapping("/division/levels/{alias}")
	List<SpbuStudyProgramDTO> getStudyPrograms(@PathVariable(name = "alias") String alias);

	/**
	 * Получение студенческих групп.
	 * @param id Id программы обучения
	 * @return	 список студенческих групп
	 */
	@GetMapping("division/{id}/groups")
	List<SpbuTeamDTO> getGroups(@PathVariable(name = "id") String id);

	/**
	 * Получение заниятий на следующую неделю.
	 * @param id Id студенческой группы
	 * @return	 список занятий
	 */
	@GetMapping("group/{id}/events")
	List<SpbuEventDTO> getNextWeekEventsById(@PathVariable(name = "id") String id);

	/**
	 * Получение списка занятий за определенный период.
	 * @param id 		Id студенческой группы
	 * @param startTime начало периода
	 * @param endTime 	конец периода
	 * @return 			список занятий
	 */
	@GetMapping("group/{id}/events/{start}/{end}")
	List<SpbuEventDTO> getEventsByIdForTimeInterval(@PathVariable(name = "id")String id,
													   @PathVariable(name = "start") String startTime,
													   @PathVariable(name = "end") String endTime);

	/**
	 * Получение заниятий на следующую неделю.
	 * @param name 		имя студенческой группы
	 * @return 			список занятий
	 */
	@GetMapping("group/name/{name}/events")
	List<SpbuEventDTO> getNextWeekEventsByName(@PathVariable(name = "name") String name);

	/**
	 * Получение списка занятий за определенный период.
	 * @param name 		имя студенческой группы
	 * @param startTime начало периода
	 * @param endTime 	конец периода
	 * @return 			список занятий
	 */
	@GetMapping("group/name/{name}/events/{start}/{end}")
	List<SpbuEventDTO> getEventsByNameForTimeInterval(@PathVariable(name = "name")String name,
													@PathVariable(name = "start") String startTime,
													@PathVariable(name = "end") String endTime);

	/**
	 * Сохраняет в БД все группы для выбранного направления.
	 * @param alias Сокращённое наименование подготовки
	 * @return 		Ответ с кодом 200 и сообщением о результате сохранения
	 */
	@GetMapping("division/{alias}/saveAllGroups")
	ResponseEntity<String> saveAllGroupsToDB(@PathVariable(name = "alias") String alias);

}

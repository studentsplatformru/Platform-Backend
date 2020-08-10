package ru.studentsplatform.backend.university.schedule.spbu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuDivisionDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuEventDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuGroupDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuStudyProgramDTO;
import ru.studentsplatform.backend.service.proxy.SpbuProxy;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuService;

import java.util.List;

/**
 * Класс, содержащий контроллеры для получения сведений из API СПБГУ.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 10.08.2020
 */
@RestController
@RequestMapping("/spbu")
public class SpbuDataController {

	private final SpbuProxy proxy;
	private final SpbuService service;


	public SpbuDataController(SpbuProxy proxy, SpbuService service) {
		this.proxy = proxy;
		this.service = service;
	}

	/**
	 * Получение списка направлений поготовки.
	 * @return список DTO направлений подготовки
	 */
	@GetMapping("/divisions")
	List<SpbuDivisionDTO> getDivisions() {

		return proxy.getDivisions();
	}

	/**
	 * Получение программ обучения.
	 * @param alias Сокращённое наименование направления подготовки
	 * @return список DTO направлений подготовки
	 */
	@GetMapping("/division/levels/{alias}")
	List<SpbuStudyProgramDTO> getProgramLevels(@PathVariable(name = "alias") String alias) {
		return service.studyProgramUnwrap(proxy.getProgramLevels(alias));
	}

	/**
	 * Получение студенческих групп.
	 * @param id Id программы обучения
	 * @return список студенческих групп
	 */
	@GetMapping("division/{id}/groups")
	public List<SpbuGroupDTO> getGroups(@PathVariable(name = "id") String id) {
		return proxy.getGroups(id).getGroups();
	}

	/**
	 * Получение заниятий на следующую неделю.
	 * @param id Id студенческой группы
	 * @return список занятий
	 */
	@GetMapping("group/{id}/events")
	public List<SpbuEventDTO> getCurrentWeekEvents(@PathVariable(name = "id") String id) {
		return service.eventUnwrap(proxy.getDays(id).getDays());
	}

	/**
	 * Получение списка занятий за определенный период.
	 * @param id Id студенческой группы
	 * @param startTime начало периода
	 * @param endTime конец периода
	 * @return список занятий
	 */
	@GetMapping("group/{id}/events/{start}/{end}")
	public List<SpbuEventDTO> getEventsForTimeInterval(@PathVariable(name = "id")String id,
													   @PathVariable(name = "start") String startTime,
													   @PathVariable(name = "end") String endTime) {
		return service.eventUnwrap(proxy.getDays(id, startTime, endTime).getDays());
	}
}

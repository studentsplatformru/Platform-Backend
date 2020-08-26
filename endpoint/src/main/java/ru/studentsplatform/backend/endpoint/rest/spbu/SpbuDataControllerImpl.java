package ru.studentsplatform.backend.endpoint.rest.spbu;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuDivisionDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuEventDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuStudyProgramDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuTeamDTO;
import ru.studentsplatform.backend.domain.repository.spbu.SpbuEventRepository;
import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;
import ru.studentsplatform.backend.service.proxy.FeignConfig;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;
import ru.studentsplatform.backend.university.schedule.spbu.mapper.SpbuEventMapper;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuEventService;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuTeamService;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuUnwrapService;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuUtilService;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс контроллера для получения сведений из API СПБГУ.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 10.08.2020
 */
@Profiled
@RestController
@RequestMapping("/spbu")
public class SpbuDataControllerImpl implements SpbuDataController {

	private final SpbuEventRepository repository;

	private final SpbuTeamService teamService;

	private final SpbuUnwrapService unwrapService;

	private final SpbuEventService eventService;

	private final SpbuUtilService utilService;

	private final SpbuEventMapper mapper;
	/**
	 * Конструктор.
	 * @param teamService 	Сервис SpbuTeam
	 * @param unwrapService Сервис для разворачивания классов-обёрток данных SPBU
 	 * @param eventService 	Сервис SpbuEvent
	 * @param utilService	Сервис SpbuUtil
	 * @param mapper		Маппер SpbuEvent
	 */
	public SpbuDataControllerImpl(SpbuEventRepository repository,
								  SpbuTeamService teamService,
								  SpbuUnwrapService unwrapService,
								  SpbuEventService eventService,
								  SpbuUtilService utilService,
								  SpbuEventMapper mapper) {
		this.repository = repository;
		this.teamService = teamService;
		this.unwrapService = unwrapService;
		this.eventService = eventService;
		this.utilService = utilService;
		this.mapper = mapper;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<SpbuDivisionDTO>> getDivisions() {
		return ResponseEntity.ok(FeignConfig.getSpbuProxy().getDivisions());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<SpbuStudyProgramDTO>> getStudyPrograms(String alias) {
		try {
			return ResponseEntity.ok(unwrapService
					.studyProgramUnwrap(FeignConfig.getSpbuProxy().getProgramLevels(alias)));
		} catch (NullPointerException e) {
			return ResponseEntity.ok(new LinkedList<>());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<SpbuTeamDTO>> getGroups(String id) {
		try {
			return ResponseEntity.ok(FeignConfig.getSpbuProxy().getGroups(id).getGroups());
		} catch (NullPointerException e) {
			return ResponseEntity.ok(new LinkedList<>());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<SpbuEventDTO>> getEventsForWeek(String groupName) {
		var dtos = mapper.listSpbuEventToSpbuEventDTO(eventService.getEventsByCurrentWeek(groupName));
		return ResponseEntity.ok(dtos);
	}

	@Override
	public ResponseEntity<List<SpbuEventDTO>> getEventsByDay(String groupName, LocalDate day) {
		SpbuTeam team = teamService.getByName(groupName);
		var dtos = mapper.listSpbuEventToSpbuEventDTO(repository.findByDateAndTeam(day, team));
		return ResponseEntity.ok(dtos);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<SpbuEventDTO>> getEventsByInterval(String name,
																  String startTime,
																  String endTime) {
		var result = mapper.listSpbuEventToSpbuEventDTO(eventService.getEventsByInterval(name, startTime, endTime));
		return ResponseEntity.ok(result);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<String> saveAllGroupsForAlias(String alias) {
		teamService.saveAllAliasGroups(alias);
		return ResponseEntity.ok("Groups saving started for alias: " + alias);
	}

	@Override
	public ResponseEntity<String> saveAllGroups() {
		teamService.saveAllGroups();
		return ResponseEntity.ok("Groups saving started!");
	}

	@Override
	public ResponseEntity<Boolean> stressTest(int iterations) {
		return ResponseEntity.ok(utilService.stressTest(iterations));
	}
}

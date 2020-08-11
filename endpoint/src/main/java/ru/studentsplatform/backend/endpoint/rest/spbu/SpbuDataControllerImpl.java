package ru.studentsplatform.backend.endpoint.rest.spbu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuDivisionDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuEventDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuTeamDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuStudyProgramDTO;
import ru.studentsplatform.backend.endpoint.mapper.spbu.SpbuTeamMapper;
import ru.studentsplatform.backend.service.proxy.SpbuProxy;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuService;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс, содержащий контроллеры для получения сведений из API СПБГУ.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 10.08.2020
 */
@RestController
@RequestMapping("/spbu")
public class SpbuDataControllerImpl implements SpbuDataController {

	private final Logger logger = LoggerFactory.getLogger(SpbuDataControllerImpl.class);

	private final SpbuProxy proxy;
	private final SpbuService service;
	private final SpbuTeamMapper mapper;

	public SpbuDataControllerImpl(SpbuProxy proxy, SpbuService service, SpbuTeamMapper mapper) {
		this.proxy = proxy;
		this.service = service;
		this.mapper = mapper;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SpbuDivisionDTO> getDivisions() {
			return proxy.getDivisions();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SpbuStudyProgramDTO> getStudyPrograms(String alias) {
		try {
			return service.studyProgramUnwrap(proxy.getProgramLevels(alias));
		} catch (NullPointerException e) {
			return new LinkedList<>();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SpbuTeamDTO> getGroups(String id) {
		try {
			return proxy.getGroups(id).getGroups();
		} catch (NullPointerException e) {
			return new LinkedList<>();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SpbuEventDTO> getNextWeekEventsById(String id) {
		try {
			return service.eventUnwrap(proxy.getDays(id).getDays());
		} catch (NullPointerException e) {
			return new LinkedList<>();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SpbuEventDTO> getEventsByIdForTimeInterval(String id, String startTime, String endTime) {
		try {
			return service.eventUnwrap(proxy.getDays(id, startTime, endTime).getDays());
		} catch (NullPointerException e) {
			return new LinkedList<>();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SpbuEventDTO> getNextWeekEventsByName(String name) {
		try {
			var id = service.getByName(name).getId().toString();
			return service.eventUnwrap(proxy.getDays(id).getDays());
		} catch (NullPointerException e) {
			return new LinkedList<>();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SpbuEventDTO> getEventsByNameForTimeInterval(String name, String startTime, String endTime) {
		try {
			var id = service.getByName(name).getId().toString();
			return service.eventUnwrap(proxy.getDays(id, startTime, endTime).getDays());
		} catch (NullPointerException e) {
			return new LinkedList<>();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ResponseEntity<String> saveAllGroupsToDB(String alias) {

			for (SpbuStudyProgramDTO program : getStudyPrograms(alias)) {
				try {
					for (SpbuTeamDTO group : getGroups(program.getProgramId().toString())) {
						group.setAlias(alias);
						service.create(mapper.spbuTeamDTOToSpbuTeam(group));
						Thread.sleep(100);
					}
				} catch (NullPointerException | InterruptedException ignored) {
					logger.error("Error occurred while group saving!");
					return ResponseEntity.ok("Error occurred while saving roups for alias " + alias);
				}

			}
			return ResponseEntity.ok("Groups saved for alias " + alias);
	}
}

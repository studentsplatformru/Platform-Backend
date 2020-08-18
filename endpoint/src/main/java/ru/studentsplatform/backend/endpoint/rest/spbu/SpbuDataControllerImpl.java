package ru.studentsplatform.backend.endpoint.rest.spbu;

import com.google.common.cache.LoadingCache;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuDivisionDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuEventDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuStudyProgramDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuTeamDTO;
import ru.studentsplatform.backend.entities.model.spbu.SpbuEvent;
import ru.studentsplatform.backend.service.proxy.FeignConfig;
import ru.studentsplatform.backend.system.exception.core.Fault;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuTeamService;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuUnwrapService;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Класс контроллера для получения сведений из API СПБГУ.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 10.08.2020
 */
@Profiled
@RestController
@RequestMapping("/spbu")
public class SpbuDataControllerImpl implements SpbuDataController {

	private final SpbuTeamService teamService;

	private final SpbuUnwrapService unwrapService;

	private final LoadingCache<String, List<SpbuEvent>> cacheLoader;

	/**
	 * Конструктор.
	 * @param teamService Сервис SpbuTeam
	 * @param unwrapService Сервис для разворачивания классов-обёрток данных SPBU
 	 * @param cacheLoader Загрузчик кэша SpvuEvent
	 */
	public SpbuDataControllerImpl(SpbuTeamService teamService,
								  SpbuUnwrapService unwrapService,
								  LoadingCache<String, List<SpbuEvent>> cacheLoader) {
		this.teamService = teamService;
		this.unwrapService = unwrapService;
		this.cacheLoader = cacheLoader;
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
	public ResponseEntity<List<SpbuEventDTO>> getEventsByIdForTimeInterval(String id,
																		   String startTime,
																		   String endTime) {
		try {
			return ResponseEntity.ok(unwrapService.eventUnwrap(FeignConfig.getSpbuProxy()
					.getDays(id, startTime, endTime).getDays()));
		} catch (NullPointerException e) {
			return ResponseEntity.ok(new LinkedList<>());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<SpbuEventDTO>> getEventsByNameForTimeInterval(String name,
																			 String startTime,
																			 String endTime) {
		try {
			var id = teamService.getByName(name).getId().toString();
			return ResponseEntity
					.ok(unwrapService
							.eventUnwrap(FeignConfig.getSpbuProxy().getDays(id, startTime, endTime).getDays()));
		} catch (NullPointerException e) {
			return ResponseEntity.ok(new LinkedList<>());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<String> saveAllGroupsToDB(String alias) {
		teamService.saveAllAliasGroups(alias);
		return ResponseEntity.ok("Groups saving started for alias: " + alias);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<SpbuEvent>> getNextWeekEvents(String groupName) throws Fault {
		try {
			return ResponseEntity.ok(cacheLoader.get(groupName));
		} catch (ExecutionException e) {
			throw new Fault(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<SpbuEvent>> refreshEvents(String groupName) throws Fault {
		cacheLoader.refresh(groupName);
		try {
			return ResponseEntity.ok(cacheLoader.get(groupName));
		} catch (ExecutionException e) {
			throw new Fault(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Очищает кэш каждую полночь с воскресенья на понедельник.
	 * (0 sec, 0 min, 0 hrs, any day of month, every month, 2nd day of week)
	 */
	@Scheduled(cron = "0 0 0 ? * 2")
	private void dropCacheWeekly() {
		cacheLoader.invalidateAll();
	}
}

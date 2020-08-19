package ru.studentsplatform.backend.university.schedule.spbu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.domain.repository.spbu.SpbuEventRepository;
import ru.studentsplatform.backend.entities.model.spbu.SpbuEvent;
import ru.studentsplatform.backend.service.proxy.FeignConfig;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;
import ru.studentsplatform.backend.university.schedule.spbu.mapper.SpbuEventMapper;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuEventService;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuTeamService;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuUnwrapService;

import java.time.LocalDate;
import java.util.List;

/**
 * Имплементация CRUD сервиса SpbuEvent.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 16.08.2020
 */
@Slf4j
@Service
@Profiled
public class SpbuEventServiceImpl implements SpbuEventService {

	private final SpbuEventRepository repository;

	private final SpbuEventMapper mapper;

	private final SpbuUnwrapService unwrapService;

	private final SpbuTeamService teamService;

	/**
	 * Конструктор.
	 * @param repository 	Репозиторий SpbuEvent
	 * @param mapper	 	Маппер SpbuEvent
	 * @param unwrapService Сервис по разворачиванию классов обёрток СПБГУ
	 * @param teamService 	Сервис SpbuTeam
	 */
	public SpbuEventServiceImpl(SpbuEventRepository repository, SpbuEventMapper mapper,
								SpbuUnwrapService unwrapService, SpbuTeamService teamService) {
		this.repository = repository;
		this.mapper = mapper;
		this.unwrapService = unwrapService;
		this.teamService = teamService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SpbuEvent create(SpbuEvent entity) {
		log.info("saving event: {}, {}", entity.getSubject(), entity.getDayWithTimeInterval());
		entity.setTeam(teamService.getByName(entity.getTeam().getName()));
		return repository.save(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SpbuEvent> getByGroupName(String groupName) {
		return repository.findByTeamName(groupName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Cacheable("DayEvents")
	public List<SpbuEvent> getEvents(String teamName, LocalDate day) {
		String dayStart = String.format("%d-%d-%d", day.getDayOfMonth(), day.getMonth().getValue(), day.getYear());
		day = day.plusDays(1);
		String dayEnd = String.format("%d-%d-%d", day.getDayOfMonth(), day.getMonth().getValue(), day.getYear());

		String id = teamService.getByName(teamName).getId().toString();

		var eventDTOList = unwrapService.eventUnwrap(FeignConfig.getSpbuProxy()
				.getDays(id, dayStart, dayEnd).getDays());

		eventDTOList.forEach((event) -> event.setSpbuTeamName(teamName));
		var eventList = mapper.listSpbuEventDTOToSpbuEvent(eventDTOList);
		eventList.forEach(this::create);

		return eventList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Cacheable("WeekEvents")
	public List<SpbuEvent> getEvents(String teamName) {
		var eventDTOList = unwrapService.eventUnwrap(FeignConfig.getSpbuProxy()
				.getDays(teamService.getByName(teamName).getId().toString()).getDays());
		eventDTOList.forEach((event) -> event.setSpbuTeamName(teamName));
		var eventList = mapper.listSpbuEventDTOToSpbuEvent(eventDTOList);
		eventList.forEach(this::create);
		return eventList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Cacheable("DateEvents")
	public List<SpbuEvent> getEvents(String teamName, String startDate, String endDate) {
		String id = teamService.getByName(teamName).getId().toString();

		var eventDTOList = unwrapService.eventUnwrap(FeignConfig.getSpbuProxy()
				.getDays(id, startDate, endDate).getDays());

		eventDTOList.forEach((event) -> event.setSpbuTeamName(teamName));
		var eventList = mapper.listSpbuEventDTOToSpbuEvent(eventDTOList);
		eventList.forEach(this::create);

		return eventList;
	}

	/**
	 * Очищает кэш для текущей недели каждую полночь с воскресенья на понедельник.
	 */
	@Scheduled(cron = "0 0 0 ? * 2")
	@CacheEvict(value = "WeekEvents", allEntries = true)
	public void clearWeekCache() {
		log.info("Week cache evicted!");
	}

}

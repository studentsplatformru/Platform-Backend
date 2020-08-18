package ru.studentsplatform.backend.university.schedule.spbu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.domain.repository.spbu.SpbuEventRepository;
import ru.studentsplatform.backend.entities.model.spbu.SpbuEvent;
import ru.studentsplatform.backend.service.proxy.FeignConfig;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;
import ru.studentsplatform.backend.university.schedule.spbu.mapper.SpbuEventMapper;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuEventService;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuTeamService;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuUnwrapService;

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
	 * @param repository репозиторий SpbuEvent
	 * @param mapper маппер SpbuEvent
	 * @param unwrapService сервис по разворачиванию классов обёрток СПБГУ
	 * @param teamService сервис SpbuTeam
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
		log.info("saving event: {}\n {}", entity.getSubject(), entity.getDayWithTimeInterval());
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
	public List<SpbuEvent> getWeekEvents(String teamName) throws feign.RetryableException {
		var eventDTOList = unwrapService.eventUnwrap(FeignConfig.getSpbuProxy()
				.getDays(teamService.getByName(teamName).getId().toString()).getDays());
		eventDTOList.forEach((event) -> event.setSpbuTeamName(teamName));
		var eventList = mapper.listSpbuEventDTOToSpbuEvent(eventDTOList);
		eventList.forEach(this::create);
		return eventList;
	}

}

package ru.studentsplatform.backend.university.schedule.spbu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuEventDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuEventTransfer;
import ru.studentsplatform.backend.domain.repository.spbu.SpbuEventRepository;
import ru.studentsplatform.backend.entities.model.spbu.SpbuEvent;
import ru.studentsplatform.backend.service.proxy.FeignConfig;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;
import ru.studentsplatform.backend.university.schedule.spbu.mapper.SpbuEventMapper;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuEventService;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuTeamService;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuUnwrapService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
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
		log.info("saving event: {}, {}", entity.getSubject(), entity.getStartTime());
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
	public List<SpbuEvent> getEventsByDay(String teamName, LocalDate day) {
		String dayStart = String.format("%d-%d-%d", day.getDayOfMonth(), day.getMonth().getValue(), day.getYear());
		day = day.plusDays(1);
		String dayEnd = String.format("%d-%d-%d", day.getDayOfMonth(), day.getMonth().getValue(), day.getYear());

		String id = teamService.getByName(teamName).getId().toString();

		var eventTransferList = unwrapService.eventUnwrap(FeignConfig.getSpbuProxy()
				.getDays(id, dayStart, dayEnd).getDays());

		return transferAndSave(eventTransferList, teamName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Cacheable("WeekEvents")
	public List<SpbuEvent> getEventsByCurrentWeek(String teamName) {
		var eventTransferList = unwrapService.eventUnwrap(FeignConfig.getSpbuProxy()
				.getDays(teamService.getByName(teamName).getId().toString()).getDays());

		return transferAndSave(eventTransferList, teamName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Cacheable("DateEvents")
	public List<SpbuEvent> getEventsByInterval(String teamName, String startDate, String endDate) {
		String id = teamService.getByName(teamName).getId().toString();

		var eventTransferList = unwrapService.eventUnwrap(FeignConfig.getSpbuProxy()
				.getDays(id, startDate, endDate).getDays());

		return transferAndSave(eventTransferList, teamName);
	}

	/**
	 * Подготавливает и сохраняет сущность SpbuEvent.
	 * @param transfers Список объектов, полученных из БД СПБГУ
	 * @param teamName 	Имя студенческой группы СПБГУ
	 * @return 			Список сохраненных объектов
	 */
	private List<SpbuEvent> transferAndSave(List<SpbuEventTransfer> transfers, String teamName) {
		List<SpbuEventDTO> eventDTOList = transferEvent(transfers);

		eventDTOList.forEach((event) -> event.setTeamName(teamName));
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

	/**
	 * Преобразует данные СПБГУ для записи в базу данных.
	 * @param spbuTransferList Список объектов, полученных из БД СПБГУ
	 * @return Список объектов, которые могут быть преобразованы в сущность SpbuEvent
	 */
	private LinkedList<SpbuEventDTO> transferEvent(List<SpbuEventTransfer> spbuTransferList) {
		LinkedList<SpbuEventDTO> events = new LinkedList<>();
		for (SpbuEventTransfer to: spbuTransferList) {
			SpbuEventDTO dto = new SpbuEventDTO();

			dto.setTeamName(to.getSpbuTeamName());
			dto.setSubject(to.getSubject());
			dto.setEducator(to.getEducator());
			dto.setLocation(to.getLocation());

			LocalDate localDate = LocalDate.parse(to.getStartTime().substring(0, 10));
			dto.setDate(localDate);

			LocalTime dateTime = LocalTime.parse(to.getStartTime().substring(11));
			dto.setStartTime(dateTime);
			dateTime = LocalTime.parse(to.getEndTime().substring(11));
			dto.setEndTime(dateTime);
			events.add(dto);
		}
		return events;
	}

}

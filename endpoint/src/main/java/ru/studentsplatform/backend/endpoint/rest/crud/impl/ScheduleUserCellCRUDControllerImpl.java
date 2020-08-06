package ru.studentsplatform.backend.endpoint.rest.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.dto.ScheduleUserCellFilterDTO;
import ru.studentsplatform.backend.domain.dto.schedule.ScheduleUserCellDTO;
import ru.studentsplatform.backend.endpoint.mapper.ScheduleUserCellMapper;
import ru.studentsplatform.backend.endpoint.rest.crud.ScheduleUserCellCRUDController;
import ru.studentsplatform.backend.service.crud.impl.ScheduleUserCellServiceImpl;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.List;

@Profiled
@RestController
@RequestMapping(ScheduleUserCellCRUDController.BASE_URL)
public class ScheduleUserCellCRUDControllerImpl implements ScheduleUserCellCRUDController {
	private final ScheduleUserCellMapper scheduleUserCellMapper;

	private final ScheduleUserCellServiceImpl scheduleUserCellService;

	/**
	 * Конструктор.
	 *
	 * @param scheduleUserCellMapper  маппер, преобразующий ScheduleUserCellDTO в сущность ScheduleUserCell и наоборот.
	 * @param scheduleUserCellService CRUD сервис ScheduleUserCell.
	 */
	public ScheduleUserCellCRUDControllerImpl(ScheduleUserCellMapper scheduleUserCellMapper,
											  ScheduleUserCellServiceImpl scheduleUserCellService) {
		this.scheduleUserCellMapper = scheduleUserCellMapper;
		this.scheduleUserCellService = scheduleUserCellService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<ScheduleUserCellDTO> create(ScheduleUserCellDTO dto) {
		var scheduleUserCell = scheduleUserCellMapper.scheduleUserCellDTOToScheduleUserCell(dto);
		scheduleUserCell = scheduleUserCellService.create(scheduleUserCell);
		var result = scheduleUserCellMapper.scheduleUserCellToScheduleUserCellDTO(scheduleUserCell);
		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<ScheduleUserCellDTO> getById(Long id) {
		return ResponseEntity.ok(scheduleUserCellMapper.
				scheduleUserCellToScheduleUserCellDTO(scheduleUserCellService.getById(id)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<ScheduleUserCellDTO>> getAll() {
		return ResponseEntity.ok(scheduleUserCellMapper.
				listScheduleUserCellToScheduleUserCellDTO(scheduleUserCellService.getAll()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<ScheduleUserCellDTO> update(ScheduleUserCellDTO dto, Long id) {
		var entity = scheduleUserCellMapper.scheduleUserCellDTOToScheduleUserCell(dto);
		return ResponseEntity.ok(scheduleUserCellMapper
				.scheduleUserCellToScheduleUserCellDTO(scheduleUserCellService.update(entity, id)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<Boolean> delete(Long id) {
		return ResponseEntity.ok(scheduleUserCellService.delete(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<ScheduleUserCellDTO>> getFiltered(ScheduleUserCellFilterDTO filter) {
		return ResponseEntity.ok(scheduleUserCellMapper.listScheduleUserCellToScheduleUserCellDTO(
				scheduleUserCellService.getFiltered(filter)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<String> getFilteredPresencePercent(ScheduleUserCellFilterDTO filter) {
		filter.setPresence(null);
		int allUserLessons = scheduleUserCellService.getFiltered(filter).size();
		filter.setPresence(true);
		int lessonsWithPresence = scheduleUserCellService.getFiltered(filter).size();

		double percent = ((double) lessonsWithPresence / allUserLessons) * 100;
		return ResponseEntity.ok(percent + "%");
	}
}

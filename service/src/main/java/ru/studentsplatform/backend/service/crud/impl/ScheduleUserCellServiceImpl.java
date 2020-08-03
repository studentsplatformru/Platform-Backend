package ru.studentsplatform.backend.service.crud.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.studentsplatform.backend.domain.repository.ScheduleUserCellRepository;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleUserCell;
import ru.studentsplatform.backend.service.crud.ScheduleUserCellService;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.system.exception.core.BusinessException;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.List;

@Profiled
@Service
@Transactional
public class ScheduleUserCellServiceImpl implements ScheduleUserCellService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleUserCellServiceImpl.class);

	private final ScheduleUserCellRepository scheduleUserCellRepository;

	/**
	 * Конструктор.
	 *
	 * @param scheduleUserCellRepository репозиторий пользователь
	 */
	public ScheduleUserCellServiceImpl(ScheduleUserCellRepository scheduleUserCellRepository) {
		this.scheduleUserCellRepository = scheduleUserCellRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScheduleUserCell create(ScheduleUserCell newEntity) {
		return scheduleUserCellRepository.save(newEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScheduleUserCell getById(Long id) {
		return scheduleUserCellRepository.findById(id).orElseThrow(() ->
				new BusinessException(ServiceExceptionReason.SCHEDULE_USER_CELL_NOT_FOUND, id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ScheduleUserCell> getAll() {
		return scheduleUserCellRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScheduleUserCell update(ScheduleUserCell updatedEntity, Long id) {
		if (!scheduleUserCellRepository.existsById(id)) {
			throw new BusinessException(ServiceExceptionReason.SCHEDULE_USER_CELL_NOT_FOUND, id);
		}
		updatedEntity.setId(id);
		return scheduleUserCellRepository.saveAndFlush(updatedEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(Long id) {
		try {
			scheduleUserCellRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Error occured: cannot delete non-existent schedule user cell");
			return false;
		}
		return true;
	}
}

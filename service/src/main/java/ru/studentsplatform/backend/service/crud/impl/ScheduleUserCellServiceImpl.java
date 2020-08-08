package ru.studentsplatform.backend.service.crud.impl;

import com.querydsl.core.BooleanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.studentsplatform.backend.domain.pojo.filters.ScheduleUserCellFilterPOJO;
import ru.studentsplatform.backend.domain.repository.ScheduleUserCellRepository;
import ru.studentsplatform.backend.entities.model.schedule.QScheduleUserCell;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleUserCell;
import ru.studentsplatform.backend.service.crud.ScheduleUserCellService;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.system.exception.core.BusinessException;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ScheduleUserCell> getFiltered(ScheduleUserCellFilterPOJO filter) {
		//Создаём query объект
		QScheduleUserCell userCell = QScheduleUserCell.scheduleUserCell;
		BooleanBuilder where = new BooleanBuilder();

		// Если фильтр == null - пропускаем проверку с его участием
		if (filter.getUserId() != null) {
			where.and(userCell.user.id.eq(filter.getUserId()));
		}
		if (filter.getSubjectId() != null) {
			where.and(userCell.scheduleCell.subject.id.eq(filter.getSubjectId()));
		}
		if (filter.getScheduleCellId() != null) {
			where.and(userCell.scheduleCell.id.eq(filter.getScheduleCellId()));
		}
		if (filter.getStartTime() != null) {
			where.and(userCell.scheduleCell.startClass.goe(filter.getStartTime()));
		}
		if (filter.getEndTime() != null) {
			where.and(userCell.scheduleCell.endClass.loe(filter.getEndTime()));
		}

		if (filter.getDisciplineId() != null) {
			where.and(userCell.discipline.id.eq(filter.getDisciplineId()));
		}

		if (filter.getSemester() != null) {
			where.and(userCell.discipline.semester.eq(filter.getSemester()));
		}

		if (filter.getPresence() != null) {
			where.and(userCell.presence.eq(filter.getPresence()));
		}

		return StreamSupport.stream(scheduleUserCellRepository.findAll(where).spliterator(),
				false).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getFilteredPercentage(ScheduleUserCellFilterPOJO filter) {
		filter.setPresence(null);
		double allUserLessons = getFiltered(filter).size();
		filter.setPresence(true);
		double lessonsWithPresence = getFiltered(filter).size();

		double percent = (lessonsWithPresence / allUserLessons) * 100;
		return percent + "%";
	}
}

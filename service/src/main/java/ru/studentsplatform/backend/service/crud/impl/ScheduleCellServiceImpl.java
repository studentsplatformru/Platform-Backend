package ru.studentsplatform.backend.service.crud.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.studentsplatform.backend.domain.repository.ScheduleCellRepository;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleCell;
import ru.studentsplatform.backend.service.crud.ScheduleCellService;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.system.annotation.Profiled;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import java.util.List;

@Profiled
@Transactional
@Service
public class ScheduleCellServiceImpl implements ScheduleCellService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleCellServiceImpl.class);

    private final ScheduleCellRepository scheduleCellRepository;

    /**
     * Конструктор.
     * @param scheduleCellRepository репозиторий пользователь
     */
    public ScheduleCellServiceImpl(ScheduleCellRepository scheduleCellRepository) {
        this.scheduleCellRepository = scheduleCellRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScheduleCell create(ScheduleCell newEntity) {
        return scheduleCellRepository.save(newEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScheduleCell getById(Long id) {
        return scheduleCellRepository.findById(id).orElseThrow(() ->
                new BusinessException(ServiceExceptionReason.SCHEDULE_CELL_NOT_FOUND, id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ScheduleCell> getAll() {
        return scheduleCellRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScheduleCell update(ScheduleCell updatedEntity, Long id) {
        if (!scheduleCellRepository.existsById(id)) {
            throw new BusinessException(ServiceExceptionReason.SCHEDULE_CELL_NOT_FOUND, id);
        }
        updatedEntity.setId(id);
        return scheduleCellRepository.saveAndFlush(updatedEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Long id) {
        try {
            scheduleCellRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Error occured: cannot delete non-existent schedule cell");
            return false;
        }
        return true;
    }
}

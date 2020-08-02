package ru.studentsplatform.backend.service.crud.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.studentsplatform.backend.domain.repository.DirectionRepository;
import ru.studentsplatform.backend.domain.repository.FacultyRepository;
import ru.studentsplatform.backend.entities.model.university.Direction;
import ru.studentsplatform.backend.service.crud.DirectionService;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.system.annotation.Profiled;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import java.util.List;

/**
 * @author Archie-Vian (sas-aramonov@yandex.ru) 01.08.2020
 */
@Transactional
@Profiled
@Service
public class DirectionServiceImpl implements DirectionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DirectionServiceImpl.class);

    private final DirectionRepository directionRepository;
    private final FacultyRepository facultyRepository;

    public DirectionServiceImpl(DirectionRepository directionRepository, FacultyRepository facultyRepository) {
        this.directionRepository = directionRepository;
        this.facultyRepository = facultyRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Direction create(Direction newEntity) {
        if (!facultyRepository.existsById(newEntity.getFaculty().getId())) {
            throw new BusinessException(ServiceExceptionReason.FACULTY_NOT_FOUND, newEntity.getFaculty().getId());
        }
        newEntity.setFaculty(facultyRepository.getOne(newEntity.getFaculty().getId()));
        return directionRepository.save(newEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Direction getById(Long id) {
        return directionRepository.findById(id).orElseThrow(() ->
                new BusinessException(ServiceExceptionReason.DIRECTION_NOT_FOUND, id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Direction> getAll() {
        return directionRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Direction update(Direction updatedEntity, Long id) {
        updatedEntity.setId(id);
        return directionRepository.saveAndFlush(updatedEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Long id) {
        try {
            directionRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Error occurred: cannot delete non-existing direction");
            return false;
        }
        return true;
    }
}

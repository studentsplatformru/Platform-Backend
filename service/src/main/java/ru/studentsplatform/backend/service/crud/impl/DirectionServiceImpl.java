package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.entities.model.Direction;
import ru.studentsplatform.backend.repository.DirectionRepository;
import ru.studentsplatform.backend.service.crud.DirectionService;

import java.util.List;
import java.util.NoSuchElementException;

public class DirectionServiceImpl implements DirectionService {

    private final DirectionRepository directionRepository;

    public DirectionServiceImpl(DirectionRepository directionRepository) {
        this.directionRepository = directionRepository;
    }

    @Override
    public Direction create(Direction newEntity) {
        return directionRepository.saveAndFlush(newEntity);
    }

    @Override
    public Direction getById(Long id) {
        return directionRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Direction> getAll() {
        return directionRepository.findAll();
    }

    @Override
    public Direction update(Direction updatedEntity, Long id) {
        updatedEntity.setId(id);
        return directionRepository.saveAndFlush(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            directionRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

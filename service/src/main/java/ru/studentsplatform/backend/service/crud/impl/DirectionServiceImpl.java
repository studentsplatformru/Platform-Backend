package ru.studentsplatform.backend.service.crud.impl;

import ru.studentsplatform.backend.entities.model.Direction;
import ru.studentsplatform.backend.repository.DepartmentRepository;
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
        directionRepository.saveAndFlush(newEntity);
        return newEntity;
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
        Direction direction = directionRepository.findById(id).orElseThrow(NoSuchElementException::new);
        direction.setDirectionCode(updatedEntity.getDirectionCode());
        direction.setDirectionName(updatedEntity.getDirectionName());
        direction.setFaculty(updatedEntity.getFaculty());
        direction.setTeams(updatedEntity.getTeams());
        return directionRepository.saveAndFlush(direction);
    }

    @Override
    public boolean delete(Long id) {
        if (directionRepository.findById(id).isEmpty()){
            return false;
        }
        directionRepository.deleteById(id);
        return true;
    }
}

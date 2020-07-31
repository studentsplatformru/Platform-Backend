package ru.studentsplatform.backend.service.crud;

import ru.studentsplatform.backend.domain.repository.DirectionRepository;
import ru.studentsplatform.backend.entities.model.university.Direction;

import java.util.List;

public interface DirectionService extends AbstractService<Direction> {

    @Override
    Direction create(Direction newEntity);

    @Override
    Direction getById(Long id);

    @Override
    List<Direction> getAll();

    @Override
    Direction update(Direction updatedEntity, Long id);

    @Override
    boolean delete(Long id);
}

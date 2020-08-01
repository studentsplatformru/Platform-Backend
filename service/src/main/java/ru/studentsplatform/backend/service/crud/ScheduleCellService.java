package ru.studentsplatform.backend.service.crud;

import ru.studentsplatform.backend.entities.model.schedule.ScheduleCell;

import java.util.List;

public interface ScheduleCellService extends AbstractService<ScheduleCell> {
    @Override
    ScheduleCell create(ScheduleCell newEntity);

    @Override
    ScheduleCell getById(Long id);

    @Override
    List<ScheduleCell> getAll();

    @Override
    ScheduleCell update(ScheduleCell updatedEntity, Long id);

    @Override
    boolean delete(Long id);
}

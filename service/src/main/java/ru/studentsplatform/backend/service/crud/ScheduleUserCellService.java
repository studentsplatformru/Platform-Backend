package ru.studentsplatform.backend.service.crud;

import ru.studentsplatform.backend.entities.model.schedule.ScheduleUserCell;

import java.util.List;

public interface ScheduleUserCellService extends AbstractService<ScheduleUserCell> {
    @Override
    ScheduleUserCell create(ScheduleUserCell newEntity);

    @Override
    ScheduleUserCell getById(Long id);

    @Override
    List<ScheduleUserCell> getAll();

    @Override
    ScheduleUserCell update(ScheduleUserCell updatedEntity, Long id);

    @Override
    boolean delete(Long id);
}

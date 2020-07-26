package ru.studentsplatform.backend.service.crud;

import ru.studentsplatform.backend.entities.model.university.Task;

import java.util.List;

public interface TaskService extends AbstractService<Task> {

    @Override
    Task create(Task newEntity);

    @Override
    Task getById(Long id);

    @Override
    List<Task> getAll();

    @Override
    Task update(Task updatedEntity, Long id);

    @Override
    boolean delete(Long id);
}

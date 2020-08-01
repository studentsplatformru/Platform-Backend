package ru.studentsplatform.backend.service.crud;

import ru.studentsplatform.backend.entities.model.university.Subject;

import java.util.List;

public interface SubjectService extends AbstractService<Subject> {
    @Override
    Subject create(Subject newEntity);

    @Override
    Subject getById(Long id);

    @Override
    List<Subject> getAll();

    @Override
    Subject update(Subject updatedEntity, Long id);

    @Override
    boolean delete(Long id);
}

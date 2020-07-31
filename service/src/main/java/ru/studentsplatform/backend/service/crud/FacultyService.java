package ru.studentsplatform.backend.service.crud;

import ru.studentsplatform.backend.entities.model.university.Faculty;

import java.util.List;

public interface FacultyService extends AbstractService<Faculty> {

    @Override
    Faculty create(Faculty newEntity);

    @Override
    Faculty getById(Long id);

    @Override
    List<Faculty> getAll();

    @Override
    Faculty update(Faculty updatedEntity, Long id);

    @Override
    boolean delete(Long id);
}

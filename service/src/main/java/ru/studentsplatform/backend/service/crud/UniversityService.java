package ru.studentsplatform.backend.service.crud;

import ru.studentsplatform.backend.entities.model.university.University;

import java.util.List;

public interface UniversityService extends AbstractService<University> {
    @Override
    University create(University newEntity);

    @Override
    University getById(Long id);

    @Override
    List<University> getAll();

    @Override
    University update(University updatedEntity, Long id);

    @Override
    boolean delete(Long id);
}

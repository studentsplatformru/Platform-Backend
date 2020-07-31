package ru.studentsplatform.backend.service.crud;

import ru.studentsplatform.backend.entities.model.university.Department;

import java.util.List;

public interface DepartmentService extends AbstractService<Department> {

    @Override
    Department create(Department newEntity);

    @Override
    Department getById(Long id);

    @Override
    List<Department> getAll();

    @Override
    Department update(Department updatedEntity, Long id);

    @Override
    boolean delete(Long id);
}

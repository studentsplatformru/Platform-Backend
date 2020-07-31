package ru.studentsplatform.backend.service.crud;

import ru.studentsplatform.backend.entities.model.university.Discipline;

import java.util.List;

public interface DisciplineService extends AbstractService<Discipline> {



    @Override
    Discipline create(Discipline newEntity);

    @Override
    Discipline getById(Long id);

    @Override
    List<Discipline> getAll();

    @Override
    Discipline update(Discipline updatedEntity, Long id);

    @Override
    boolean delete(Long id);
}

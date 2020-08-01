package ru.studentsplatform.backend.service.crud;


import ru.studentsplatform.backend.entities.model.university.Team;

import java.util.List;

public interface TeamService extends AbstractService<Team> {
    @Override
    Team create(Team newEntity);

    @Override
    Team getById(Long id);

    @Override
    List<Team> getAll();

    @Override
    Team update(Team updatedEntity, Long id);

    @Override
    boolean delete(Long id);
}

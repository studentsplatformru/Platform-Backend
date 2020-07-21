package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.entities.model.Team;
import ru.studentsplatform.backend.repository.TeamRepository;
import ru.studentsplatform.backend.service.crud.TeamService;

import java.util.List;
import java.util.NoSuchElementException;

public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team create(Team newEntity) {
        return teamRepository.saveAndFlush(newEntity);
    }

    @Override
    public Team getById(Long id) {
        return teamRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team update(Team updatedEntity, Long id) {
        updatedEntity.setId(id);
        return teamRepository.saveAndFlush(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            teamRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

package ru.studentsplatform.backend.service.crud.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.studentsplatform.backend.domain.repository.TeamRepository;
import ru.studentsplatform.backend.entities.model.university.Team;
import ru.studentsplatform.backend.service.crud.TeamService;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.system.annotation.Profiled;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import java.util.List;

@Transactional
@Profiled
@Service
public class TeamServiceImpl implements TeamService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamServiceImpl.class);

    private final TeamRepository teamRepository;

    /**
     * Конструктор.
     * @param teamRepository репозиторий группа
     */
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Team create(Team newEntity) {
        return teamRepository.save(newEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Team getById(Long id) {
        return teamRepository.findById(id).orElseThrow(() ->
                new BusinessException(ServiceExceptionReason.TEAM_NOT_FOUND, id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Team update(Team updatedEntity, Long id) {
        if (!teamRepository.existsById(id)) {
            throw new BusinessException(ServiceExceptionReason.TEAM_NOT_FOUND, id);
        }
        updatedEntity.setId(id);
        return teamRepository.saveAndFlush(updatedEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Long id) {
        try {
            teamRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Error occured: cannot delete non-existent team");
            return false;
        }
        return true;
    }
}

package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.entities.model.Mark;
import ru.studentsplatform.backend.repository.MarkRepository;
import ru.studentsplatform.backend.service.crud.MarkService;

import java.util.List;
import java.util.NoSuchElementException;

public class MarkServiceImpl implements MarkService {
    private final MarkRepository markRepository;

    public MarkServiceImpl(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    @Override
    public Mark create(Mark newEntity) {
        return markRepository.saveAndFlush(newEntity);
    }

    @Override
    public Mark getById(Long id) {
        return markRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Mark> getAll() {
        return markRepository.findAll();
    }

    @Override
    public Mark update(Mark updatedEntity, Long id) {
        updatedEntity.setId(id);
        return markRepository.saveAndFlush(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            markRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

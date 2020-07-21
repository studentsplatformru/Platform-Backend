package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.entities.model.Homework;
import ru.studentsplatform.backend.repository.HomeworkRepository;
import ru.studentsplatform.backend.service.crud.HomeworkService;

import java.util.List;
import java.util.NoSuchElementException;

public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;

    public HomeworkServiceImpl(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    @Override
    public Homework create(Homework newEntity) {
        return homeworkRepository.saveAndFlush(newEntity);
    }

    @Override
    public Homework getById(Long id) {
        return homeworkRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Homework> getAll() {
        return homeworkRepository.findAll();
    }

    @Override
    public Homework update(Homework updatedEntity, Long id) {
        updatedEntity.setId(id);
        return homeworkRepository.saveAndFlush(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            homeworkRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

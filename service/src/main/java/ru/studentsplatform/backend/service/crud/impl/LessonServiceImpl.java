package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.entities.model.Lesson;
import ru.studentsplatform.backend.repository.LessonRepository;
import ru.studentsplatform.backend.service.crud.LessonService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Lesson create(Lesson newEntity) {
        return lessonRepository.saveAndFlush(newEntity);
    }

    @Override
    public Lesson getById(Long id) {
        return lessonRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson update(Lesson updatedEntity, Long id) {
        updatedEntity.setId(id);
        return lessonRepository.saveAndFlush(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            lessonRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

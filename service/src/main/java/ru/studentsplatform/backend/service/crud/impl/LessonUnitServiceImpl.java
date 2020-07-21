package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.entities.model.LessonUnit;
import ru.studentsplatform.backend.repository.LessonUnitRepository;
import ru.studentsplatform.backend.service.crud.LessonUnitService;

import java.util.List;
import java.util.NoSuchElementException;

public class LessonUnitServiceImpl implements LessonUnitService {

    private final LessonUnitRepository lessonUnitRepository;

    public LessonUnitServiceImpl(LessonUnitRepository lessonUnitRepository) {
        this.lessonUnitRepository = lessonUnitRepository;
    }

    @Override
    public LessonUnit create(LessonUnit newEntity) {
        return lessonUnitRepository.saveAndFlush(newEntity);
    }

    @Override
    public LessonUnit getById(Long id) {
        return lessonUnitRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<LessonUnit> getAll() {
        return lessonUnitRepository.findAll();
    }

    @Override
    public LessonUnit update(LessonUnit updatedEntity, Long id) {
        updatedEntity.setId(id);
        return lessonUnitRepository.saveAndFlush(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            lessonUnitRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

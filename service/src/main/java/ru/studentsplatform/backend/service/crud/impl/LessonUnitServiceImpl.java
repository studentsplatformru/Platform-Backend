package ru.studentsplatform.backend.service.crud.impl;

import ru.studentsplatform.backend.entities.model.Lesson;
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
        LessonUnit lessonUnit = lessonUnitRepository.findById(id).orElseThrow(NoSuchElementException::new);
        lessonUnit.setAudience(updatedEntity.getAudience());
        lessonUnit.setEndTime(updatedEntity.getEndTime());
        lessonUnit.setLessons(updatedEntity.getLessons());
        lessonUnit.setNote(updatedEntity.getNote());
        lessonUnit.setStartTime(updatedEntity.getStartTime());
        lessonUnit.setSubject(updatedEntity.getSubject());
        lessonUnit.setTeacher(updatedEntity.getTeacher());
        lessonUnit.setType(updatedEntity.getType());
        return lessonUnitRepository.saveAndFlush(lessonUnit);
    }

    @Override
    public boolean delete(Long id) {
        if (lessonUnitRepository.findById(id).isEmpty()){
            return false;
        }
        lessonUnitRepository.deleteById(id);
        return true;
    }
}

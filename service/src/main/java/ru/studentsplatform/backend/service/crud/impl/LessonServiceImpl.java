package ru.studentsplatform.backend.service.crud.impl;

import ru.studentsplatform.backend.entities.model.Lesson;
import ru.studentsplatform.backend.repository.LessonRepository;
import ru.studentsplatform.backend.service.crud.LessonService;

import java.util.List;
import java.util.NoSuchElementException;

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
        Lesson lesson = lessonRepository.findById(id).orElseThrow(NoSuchElementException::new);
        lesson.setAttendanceList(updatedEntity.getAttendanceList());
        lesson.setDate(updatedEntity.getDate());
        lesson.setHomeworkList(updatedEntity.getHomeworkList());
        lesson.setLessonUnit(updatedEntity.getLessonUnit());
        lesson.setMarks(updatedEntity.getMarks());
        lesson.setTeam(updatedEntity.getTeam());
        return lessonRepository.saveAndFlush(lesson);
    }

    @Override
    public boolean delete(Long id) {
        if (lessonRepository.findById(id).isEmpty()) {
            return false;
        }
        lessonRepository.deleteById(id);
        return true;
    }
}

package ru.studentsplatform.backend.service.crud.impl;

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
        Homework homework = homeworkRepository.findById(id).orElseThrow(NoSuchElementException::new);
        homework.setFile(updatedEntity.getFile());
        homework.setFileType(updatedEntity.getFileType());
        homework.setLesson(updatedEntity.getLesson());
        homework.setNote(updatedEntity.getNote());
        return homeworkRepository.saveAndFlush(homework);
    }

    @Override
    public boolean delete(Long id) {
        if (homeworkRepository.findById(id).isEmpty()) {
            return false;
        }
        homeworkRepository.deleteById(id);
        return true;
    }
}

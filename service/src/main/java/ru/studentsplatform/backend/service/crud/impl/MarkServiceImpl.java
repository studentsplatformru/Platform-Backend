package ru.studentsplatform.backend.service.crud.impl;

import ru.studentsplatform.backend.entities.model.Mark;
import ru.studentsplatform.backend.repository.MarkRepository;
import ru.studentsplatform.backend.service.crud.MarkService;

import java.util.List;
import java.util.NoSuchElementException;

public class MarkServiceImpl implements MarkService {

    MarkRepository markRepository;

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
        Mark mark = markRepository.findById(id).orElseThrow(NoSuchElementException::new);
        mark.setLesson(updatedEntity.getLesson());
        mark.setMarkValue(updatedEntity.getMarkValue());
        mark.setStudent(updatedEntity.getStudent());
        return markRepository.saveAndFlush(mark);
    }

    @Override
    public boolean delete(Long id) {
        if (markRepository.findById(id).isEmpty()){
            return false;
        }
        markRepository.deleteById(id);
        return true;
    }
}

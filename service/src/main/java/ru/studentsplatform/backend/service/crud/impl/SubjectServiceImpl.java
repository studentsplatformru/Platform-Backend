package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.entities.model.Subject;
import ru.studentsplatform.backend.repository.SubjectRepository;
import ru.studentsplatform.backend.service.crud.SubjectService;

import java.util.List;
import java.util.NoSuchElementException;

public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject create(Subject newEntity) {
        return subjectRepository.saveAndFlush(newEntity);
    }

    @Override
    public Subject getById(Long id) {
        return subjectRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject update(Subject updatedEntity, Long id) {
        updatedEntity.setId(id);
        return subjectRepository.saveAndFlush(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            subjectRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

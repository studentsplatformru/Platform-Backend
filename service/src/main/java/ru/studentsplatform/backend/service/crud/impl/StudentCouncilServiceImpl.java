package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.entities.model.StudentCouncil;
import ru.studentsplatform.backend.repository.StudentCouncilRepository;
import ru.studentsplatform.backend.service.crud.StudentCouncilService;

import java.util.List;
import java.util.NoSuchElementException;

public class StudentCouncilServiceImpl implements StudentCouncilService {

    private final StudentCouncilRepository studentCouncilRepository;

    public StudentCouncilServiceImpl(StudentCouncilRepository studentCouncilRepository) {
        this.studentCouncilRepository = studentCouncilRepository;
    }

    @Override
    public StudentCouncil create(StudentCouncil newEntity) {
        return studentCouncilRepository.saveAndFlush(newEntity);
    }

    @Override
    public StudentCouncil getById(Long id) {
        return studentCouncilRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<StudentCouncil> getAll() {
        return studentCouncilRepository.findAll();
    }

    @Override
    public StudentCouncil update(StudentCouncil updatedEntity, Long id) {
        updatedEntity.setId(id);
        return studentCouncilRepository.saveAndFlush(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            studentCouncilRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

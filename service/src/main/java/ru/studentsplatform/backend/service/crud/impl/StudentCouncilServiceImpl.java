package ru.studentsplatform.backend.service.crud.impl;

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
        StudentCouncil studentCouncil = studentCouncilRepository.findById(id).orElseThrow(NoSuchElementException::new);
        studentCouncil.setAudience(updatedEntity.getAudience());
        studentCouncil.setEmail(updatedEntity.getEmail());
        studentCouncil.setFaculty(updatedEntity.getFaculty());
        studentCouncil.setPhone(updatedEntity.getPhone());
        studentCouncil.setVkGroup(updatedEntity.getVkGroup());
        return studentCouncilRepository.saveAndFlush(studentCouncil);
    }

    @Override
    public boolean delete(Long id) {
        if (studentCouncilRepository.findById(id).isEmpty()){
            return false;
        }
        studentCouncilRepository.deleteById(id);
        return true;
    }
}

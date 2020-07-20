package ru.studentsplatform.backend.service.crud.impl;

import ru.studentsplatform.backend.entities.model.Student;
import ru.studentsplatform.backend.repository.StudentRepository;
import ru.studentsplatform.backend.service.crud.StudentService;

import java.util.List;
import java.util.NoSuchElementException;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student newEntity) {
        return studentRepository.saveAndFlush(newEntity);
    }

    @Override
    public Student getById(Long id) {
        return studentRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student update(Student updatedEntity, Long id) {
        Student student = studentRepository.findById(id).orElseThrow(NoSuchElementException::new);
        student.setDepartment(updatedEntity.getDepartment());
        student.setDirection(updatedEntity.getDirection());
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}

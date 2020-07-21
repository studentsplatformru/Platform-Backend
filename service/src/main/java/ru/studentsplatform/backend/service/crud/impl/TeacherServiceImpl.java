package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.entities.model.Teacher;
import ru.studentsplatform.backend.repository.TeacherRepository;
import ru.studentsplatform.backend.service.crud.TeacherService;

import java.util.List;
import java.util.NoSuchElementException;

public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher create(Teacher newEntity) {
        return teacherRepository.saveAndFlush(newEntity);
    }

    @Override
    public Teacher getById(Long id) {
        return teacherRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher update(Teacher updatedEntity, Long id) {
        updatedEntity.setId(id);
        return teacherRepository.saveAndFlush(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            teacherRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

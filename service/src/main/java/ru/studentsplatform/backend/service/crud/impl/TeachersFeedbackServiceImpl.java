package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.entities.model.TeachersFeedback;
import ru.studentsplatform.backend.repository.TeachersFeedbackRepository;
import ru.studentsplatform.backend.service.crud.TeachersFeedbackService;

import java.util.List;
import java.util.NoSuchElementException;

public class TeachersFeedbackServiceImpl implements TeachersFeedbackService {
    private final TeachersFeedbackRepository teachersFeedbackRepository;

    public TeachersFeedbackServiceImpl(TeachersFeedbackRepository teachersFeedbackRepository) {
        this.teachersFeedbackRepository = teachersFeedbackRepository;
    }

    @Override
    public TeachersFeedback create(TeachersFeedback newEntity) {
        return teachersFeedbackRepository.saveAndFlush(newEntity);
    }

    @Override
    public TeachersFeedback getById(Long id) {
        return teachersFeedbackRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<TeachersFeedback> getAll() {
        return teachersFeedbackRepository.findAll();
    }

    @Override
    public TeachersFeedback update(TeachersFeedback updatedEntity, Long id) {
        updatedEntity.setId(id);
        return teachersFeedbackRepository.saveAndFlush(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            teachersFeedbackRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

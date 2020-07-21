package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.entities.model.Faculty;
import ru.studentsplatform.backend.repository.FacultyRepository;
import ru.studentsplatform.backend.service.crud.FacultyService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty create(Faculty newEntity) {
        return facultyRepository.saveAndFlush(newEntity);
    }

    @Override
    public Faculty getById(Long id) {
        return facultyRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty update(Faculty updatedEntity, Long id) {
        updatedEntity.setId(id);
        return facultyRepository.saveAndFlush(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            facultyRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

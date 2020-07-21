package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.entities.model.University;
import ru.studentsplatform.backend.repository.UniversityRepository;
import ru.studentsplatform.backend.service.crud.UniversityService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;

    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University create(University newEntity) {
        return universityRepository.saveAndFlush(newEntity);
    }

    @Override
    public University getById(Long id) {
        return universityRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<University> getAll() {
        return universityRepository.findAll();
    }

    @Override
    public University update(University updatedEntity, Long id) {
        updatedEntity.setId(id);
        return universityRepository.saveAndFlush(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            universityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

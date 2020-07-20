package ru.studentsplatform.backend.service.crud.impl;

import ru.studentsplatform.backend.entities.model.Faculty;
import ru.studentsplatform.backend.repository.FacultyRepository;
import ru.studentsplatform.backend.service.crud.FacultyService;

import java.util.List;
import java.util.NoSuchElementException;

public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty create(Faculty newEntity) {
        facultyRepository.saveAndFlush(newEntity);
        return newEntity;
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
        Faculty faculty = facultyRepository.findById(id).orElseThrow(NoSuchElementException::new);
        faculty.setDepartments(updatedEntity.getDepartments());
        faculty.setDirections(updatedEntity.getDirections());
        faculty.setFacultyName(updatedEntity.getFacultyName());
        faculty.setJobAds(updatedEntity.getJobAds());
        faculty.setStudentCouncils(updatedEntity.getStudentCouncils());
        faculty.setUniversity(updatedEntity.getUniversity());
        return facultyRepository.saveAndFlush(faculty);
    }

    @Override
    public boolean delete(Long id) {
        if (facultyRepository.findById(id).isEmpty()){
            return false;
        }
        facultyRepository.deleteById(id);
        return true;
    }
}

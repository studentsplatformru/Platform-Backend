package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.entities.model.Department;
import ru.studentsplatform.backend.repository.DepartmentRepository;
import ru.studentsplatform.backend.service.crud.DepartmentService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department create(Department newEntity) {
        return departmentRepository.saveAndFlush(newEntity);
    }

    @Override
    public Department getById(Long id) {
        return departmentRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department update(Department updatedEntity, Long id) {
        updatedEntity.setId(id);
        return departmentRepository.saveAndFlush(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            departmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

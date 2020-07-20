package ru.studentsplatform.backend.service.crud.impl;

import ru.studentsplatform.backend.entities.model.Department;
import ru.studentsplatform.backend.repository.DepartmentRepository;
import ru.studentsplatform.backend.service.crud.DepartmentService;

import java.util.List;
import java.util.NoSuchElementException;

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
        Department department = departmentRepository.findById(id).orElseThrow(NoSuchElementException::new);
        department.setDepartmentName(updatedEntity.getDepartmentName());
        department.setFaculty(updatedEntity.getFaculty());
        department.setStudents(updatedEntity.getStudents());
        department.setTeachers(updatedEntity.getTeachers());
        return departmentRepository.saveAndFlush(department);
    }

    @Override
    public boolean delete(Long id) {
        if(departmentRepository.findById(id).isEmpty()) {
            return false;
        }
        departmentRepository.deleteById(id);
        return true;
    }
}

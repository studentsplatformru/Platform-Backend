package ru.studentsplatform.backend.service.crud.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.studentsplatform.backend.domain.repository.DepartmentRepository;
import ru.studentsplatform.backend.domain.repository.FacultyRepository;
import ru.studentsplatform.backend.entities.model.university.Department;
import ru.studentsplatform.backend.service.crud.DepartmentService;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.system.annotation.Profiled;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import java.util.List;

/**
 * @author Archie-Vian (sas-aramonov@yandex.ru) 01.08.2020
 */
@Transactional
@Profiled
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, FacultyRepository facultyRepository) {
        this.departmentRepository = departmentRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Department create(Department newEntity) {
        if (!facultyRepository.existsById(newEntity.getFaculty().getId())) {
            throw new BusinessException(ServiceExceptionReason.FACULTY_NOT_FOUND, newEntity.getFaculty().getId());
        }
        newEntity.setFaculty(facultyRepository.getOne(newEntity.getFaculty().getId()));
        return departmentRepository.save(newEntity);
    }

    @Override
    public Department getById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() ->
                new BusinessException(ServiceExceptionReason.DEPARTMENT_NOT_FOUND, id));
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
            LOGGER.error("Error occured: cannot delete non-existing department");
            return false;
        }
        return true;
    }
}

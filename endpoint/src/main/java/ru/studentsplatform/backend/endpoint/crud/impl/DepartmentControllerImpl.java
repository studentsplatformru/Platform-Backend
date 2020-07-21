package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.DepartmentDTO;
import ru.studentsplatform.backend.endpoint.crud.DepartmentController;
import ru.studentsplatform.backend.entities.model.Department;
import ru.studentsplatform.backend.mapper.DepartmentMapper;
import ru.studentsplatform.backend.service.crud.DepartmentService;

import java.util.List;

@RestController
@RequestMapping(DepartmentController.BASE_URL)
public class DepartmentControllerImpl implements DepartmentController {

    private final DepartmentService departmentService;

    private final DepartmentMapper mapper;

    public DepartmentControllerImpl(DepartmentService departmentService, DepartmentMapper mapper) {
        this.departmentService = departmentService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<DepartmentDTO> create(DepartmentDTO newInstanceRequest) {
        Department department = mapper.departmentDTOToDepartment(newInstanceRequest);
        department = departmentService.create(department);
        return ResponseEntity.ok(mapper.departmentToDepartmentDTO(department));
    }

    @Override
    public ResponseEntity<DepartmentDTO> getById(Long id) {
        Department department = departmentService.getById(id);
        DepartmentDTO departmentDTO = mapper.departmentToDepartmentDTO(department);
        return ResponseEntity.ok(departmentDTO);
    }

    @Override
    public ResponseEntity<List<DepartmentDTO>> getAll() {
        List<Department> departmentList = departmentService.getAll();
        List<DepartmentDTO> departmentDTOList = mapper.listDepartmentToDepartmentDTO(departmentList);
        return ResponseEntity.ok(departmentDTOList);
    }

    @Override
    public ResponseEntity<DepartmentDTO> update(DepartmentDTO updatedInstanceRequest, Long id) {
        Department department = mapper.departmentDTOToDepartment(updatedInstanceRequest);
        department = departmentService.update(department, id);
        return ResponseEntity.ok(mapper.departmentToDepartmentDTO(department));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(departmentService.delete(id));
    }
}

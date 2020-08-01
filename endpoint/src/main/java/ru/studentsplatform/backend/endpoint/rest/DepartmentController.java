package ru.studentsplatform.backend.endpoint.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.studentsplatform.backend.domain.dto.university.DepartmentDTO;
import ru.studentsplatform.backend.entities.model.university.Department;

import java.util.List;

public interface DepartmentController extends AbstractController<DepartmentDTO> {

    String BASE_URL = AbstractController.BASE_URL + "/department";

    @Override
    ResponseEntity<DepartmentDTO> create(DepartmentDTO departmentDTO);

    @Override
    ResponseEntity<DepartmentDTO> getById(Long id);

    @Override
    ResponseEntity<List<DepartmentDTO>> getAll();

    @Override
    ResponseEntity<DepartmentDTO> update(DepartmentDTO departmentDTO, Long id);

    @Override
    ResponseEntity<Boolean> delete(Long id);
}

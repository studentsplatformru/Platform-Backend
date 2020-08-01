package ru.studentsplatform.backend.endpoint.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.studentsplatform.backend.domain.dto.university.DepartmentDTO;

import java.util.List;

public interface DepartmentController extends AbstractController<DepartmentDTO> {

    String BASE_URL = AbstractController.BASE_URL + "/department";

    @Override
    ResponseEntity<DepartmentDTO> create(@RequestBody DepartmentDTO departmentDTO);

    @Override
    ResponseEntity<DepartmentDTO> getById(@PathVariable Long id);

    @Override
    ResponseEntity<List<DepartmentDTO>> getAll();

    @Override
    ResponseEntity<DepartmentDTO> update(@RequestBody DepartmentDTO departmentDTO, @PathVariable Long id);

    @Override
    ResponseEntity<Boolean> delete(@PathVariable Long id);
}

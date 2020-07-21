package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.DepartmentDTO;

public interface DepartmentController extends AbstractController<DepartmentDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/department";
}

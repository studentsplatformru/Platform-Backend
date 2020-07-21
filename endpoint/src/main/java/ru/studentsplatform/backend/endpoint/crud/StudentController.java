package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.StudentDTO;

public interface StudentController extends AbstractController<StudentDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/student";
}

package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.FacultyDTO;

public interface FacultyController extends AbstractController<FacultyDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/faculty";
}

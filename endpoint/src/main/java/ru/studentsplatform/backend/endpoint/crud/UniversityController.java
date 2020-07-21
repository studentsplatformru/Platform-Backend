package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.UniversityDTO;

public interface UniversityController extends AbstractController<UniversityDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/university";
}

package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.SubjectDTO;

public interface SubjectController extends AbstractController<SubjectDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/subject";
}

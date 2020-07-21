package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.StudentCouncilDTO;

public interface StudentCouncilController extends AbstractController<StudentCouncilDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/student_council";
}

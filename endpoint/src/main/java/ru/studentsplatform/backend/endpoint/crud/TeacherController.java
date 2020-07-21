package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.TeacherDTO;

public interface TeacherController extends AbstractController<TeacherDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/teacher";
}

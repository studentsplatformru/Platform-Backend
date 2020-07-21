package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.LessonDTO;

public interface LessonController extends AbstractController<LessonDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/lesson";
}

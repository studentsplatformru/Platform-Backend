package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.LessonUnitDTO;

public interface LessonUnitController extends AbstractController<LessonUnitDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/lesson_unit";
}

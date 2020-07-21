package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.HomeworkDTO;

public interface HomeworkController extends AbstractController<HomeworkDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/homework";
}

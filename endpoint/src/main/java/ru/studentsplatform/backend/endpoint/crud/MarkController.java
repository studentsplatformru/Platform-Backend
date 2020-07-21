package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.MarkDTO;

public interface MarkController extends AbstractController<MarkDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/mark";
}

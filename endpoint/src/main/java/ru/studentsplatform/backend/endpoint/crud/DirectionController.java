package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.DirectionDTO;

public interface DirectionController extends AbstractController<DirectionDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/direction";
}

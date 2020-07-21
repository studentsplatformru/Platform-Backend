package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.MaterialDTO;

public interface MaterialController extends AbstractController<MaterialDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/material";
}

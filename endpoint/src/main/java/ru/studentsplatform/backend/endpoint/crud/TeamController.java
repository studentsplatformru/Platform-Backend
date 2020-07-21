package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.TeamDTO;

public interface TeamController extends AbstractController<TeamDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/team";
}

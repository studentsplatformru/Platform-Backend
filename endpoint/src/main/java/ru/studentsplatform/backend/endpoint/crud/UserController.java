package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.UserDTO;

public interface UserController extends AbstractController<UserDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/user";
}

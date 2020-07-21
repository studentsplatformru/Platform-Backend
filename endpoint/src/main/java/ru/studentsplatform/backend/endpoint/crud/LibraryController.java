package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.LibraryDTO;

public interface LibraryController extends AbstractController<LibraryDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/library";
}

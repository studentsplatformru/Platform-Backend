package ru.studentsplatform.backend.endpoint.rest;

import org.springframework.http.ResponseEntity;
import ru.studentsplatform.backend.domain.dto.university.FacultyDTO;

import java.util.List;

public interface Faculty extends AbstractController<FacultyDTO> {

    String BASE_URL = AbstractController.BASE_URL + "/faculty";

    @Override
    ResponseEntity<FacultyDTO> create(FacultyDTO facultyDTO);

    @Override
    ResponseEntity<FacultyDTO> getById(Long id);

    @Override
    ResponseEntity<List<FacultyDTO>> getAll();

    @Override
    ResponseEntity<FacultyDTO> update(FacultyDTO facultyDTO, Long id);

    @Override
    ResponseEntity<Boolean> delete(Long id);
}

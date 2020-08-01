package ru.studentsplatform.backend.endpoint.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.studentsplatform.backend.domain.dto.university.FacultyDTO;

import java.util.List;

public interface Faculty extends AbstractController<FacultyDTO> {

    String BASE_URL = AbstractController.BASE_URL + "/faculty";

    @Override
    ResponseEntity<FacultyDTO> create(@RequestBody FacultyDTO facultyDTO);

    @Override
    ResponseEntity<FacultyDTO> getById(@PathVariable Long id);

    @Override
    ResponseEntity<List<FacultyDTO>> getAll();

    @Override
    ResponseEntity<FacultyDTO> update(@RequestBody FacultyDTO facultyDTO,@PathVariable Long id);

    @Override
    ResponseEntity<Boolean> delete(Long id);
}

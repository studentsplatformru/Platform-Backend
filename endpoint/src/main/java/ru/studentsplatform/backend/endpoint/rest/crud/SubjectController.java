package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.studentsplatform.backend.domain.dto.university.SubjectDTO;

import java.util.List;

public interface SubjectController extends AbstractController<SubjectDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/subject";

    @Override
    ResponseEntity<SubjectDTO> create(@RequestBody SubjectDTO subjectDTO);

    @Override
    ResponseEntity<SubjectDTO> getById(@PathVariable Long id);

    @Override
    ResponseEntity<List<SubjectDTO>> getAll();

    @Override
    ResponseEntity<SubjectDTO> update(@RequestBody SubjectDTO subjectDTO, @PathVariable Long id);

    @Override
    ResponseEntity<Boolean> delete(@PathVariable Long id);
}

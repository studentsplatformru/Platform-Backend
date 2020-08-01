package ru.studentsplatform.backend.endpoint.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.studentsplatform.backend.domain.dto.university.DisciplineDTO;
import ru.studentsplatform.backend.entities.model.university.Discipline;

import java.util.List;

public interface DisciplineController extends AbstractController<DisciplineDTO> {

    String BASE_URL = AbstractController.BASE_URL + "/discipline";

    @Override
    ResponseEntity<DisciplineDTO> create(@RequestBody DisciplineDTO disciplineDTO);

    @Override
    ResponseEntity<DisciplineDTO> getById(@PathVariable Long id);

    @Override
    ResponseEntity<List<DisciplineDTO>> getAll();

    @Override
    ResponseEntity<DisciplineDTO> update(@RequestBody DisciplineDTO disciplineDTO, Long id);

    @Override
    ResponseEntity<Boolean> delete(@PathVariable Long id);
}

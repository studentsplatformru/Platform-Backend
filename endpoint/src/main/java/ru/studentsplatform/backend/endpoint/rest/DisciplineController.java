package ru.studentsplatform.backend.endpoint.rest;

import org.springframework.http.ResponseEntity;
import ru.studentsplatform.backend.domain.dto.university.DisciplineDTO;
import ru.studentsplatform.backend.entities.model.university.Discipline;

import java.util.List;

public interface DisciplineController extends AbstractController<DisciplineDTO> {

    String BASE_URL = AbstractController.BASE_URL + "/discipline";

    @Override
    ResponseEntity<DisciplineDTO> create(DisciplineDTO disciplineDTO);

    @Override
    ResponseEntity<DisciplineDTO> getById(Long id);

    @Override
    ResponseEntity<List<DisciplineDTO>> getAll();

    @Override
    ResponseEntity<DisciplineDTO> update(DisciplineDTO disciplineDTO, Long id);

    @Override
    ResponseEntity<Boolean> delete(Long id);
}

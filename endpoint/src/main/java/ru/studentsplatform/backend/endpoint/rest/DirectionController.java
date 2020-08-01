package ru.studentsplatform.backend.endpoint.rest;

import org.springframework.http.ResponseEntity;
import ru.studentsplatform.backend.domain.dto.university.DirectionDTO;
import ru.studentsplatform.backend.entities.model.university.Direction;

import java.util.List;

public interface DirectionController extends AbstractController<DirectionDTO> {

    String BASE_URL = AbstractController.BASE_URL + "/direction";

    @Override
    ResponseEntity<DirectionDTO> create(DirectionDTO directionDTO);

    @Override
    ResponseEntity<DirectionDTO> getById(Long id);

    @Override
    ResponseEntity<List<DirectionDTO>> getAll();

    @Override
    ResponseEntity<DirectionDTO> update(DirectionDTO directionDTO, Long id);

    @Override
    ResponseEntity<Boolean> delete(Long id);
}

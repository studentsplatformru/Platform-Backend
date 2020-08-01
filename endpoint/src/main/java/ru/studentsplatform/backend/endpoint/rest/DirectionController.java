package ru.studentsplatform.backend.endpoint.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.studentsplatform.backend.domain.dto.university.DirectionDTO;
import ru.studentsplatform.backend.entities.model.university.Direction;

import java.util.List;

public interface DirectionController extends AbstractController<DirectionDTO> {

    String BASE_URL = AbstractController.BASE_URL + "/direction";

    @Override
    ResponseEntity<DirectionDTO> create(@RequestBody DirectionDTO directionDTO);

    @Override
    ResponseEntity<DirectionDTO> getById(@PathVariable Long id);

    @Override
    ResponseEntity<List<DirectionDTO>> getAll();

    @Override
    ResponseEntity<DirectionDTO> update(@RequestBody DirectionDTO directionDTO, @PathVariable Long id);

    @Override
    ResponseEntity<Boolean> delete(@PathVariable Long id);
}

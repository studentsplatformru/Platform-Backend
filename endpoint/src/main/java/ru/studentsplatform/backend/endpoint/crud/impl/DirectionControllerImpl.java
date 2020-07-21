package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.DirectionDTO;
import ru.studentsplatform.backend.endpoint.crud.DirectionController;
import ru.studentsplatform.backend.entities.model.Direction;
import ru.studentsplatform.backend.mapper.DirectionMapper;
import ru.studentsplatform.backend.service.crud.DirectionService;

import java.util.List;

@RestController
@RequestMapping(DirectionController.BASE_URL)
public class DirectionControllerImpl implements DirectionController {

    private final DirectionService directionService;

    private final DirectionMapper mapper;

    public DirectionControllerImpl(DirectionService directionService, DirectionMapper mapper) {
        this.directionService = directionService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<DirectionDTO> create(DirectionDTO newInstanceRequest) {
        return null;
    }

    @Override
    public ResponseEntity<DirectionDTO> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<DirectionDTO>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<DirectionDTO> update(DirectionDTO updatedInstanceRequest, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return null;
    }
}

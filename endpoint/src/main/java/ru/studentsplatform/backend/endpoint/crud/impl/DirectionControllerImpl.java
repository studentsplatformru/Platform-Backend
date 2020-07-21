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
        Direction direction = mapper.directionDTOtoDirection(newInstanceRequest);
        direction = directionService.create(direction);
        return ResponseEntity.ok(mapper.directionToDirectionDTO(direction));
    }

    @Override
    public ResponseEntity<DirectionDTO> getById(Long id) {
        Direction direction = directionService.getById(id);
        DirectionDTO directionDTO = mapper.directionToDirectionDTO(direction);
        return ResponseEntity.ok(directionDTO);
    }

    @Override
    public ResponseEntity<List<DirectionDTO>> getAll() {
        List<Direction> directionList = directionService.getAll();
        List<DirectionDTO> directionDTOList = mapper.listDirectionToDirectionDTO(directionList);
        return ResponseEntity.ok(directionDTOList);
    }

    @Override
    public ResponseEntity<DirectionDTO> update(DirectionDTO updatedInstanceRequest, Long id) {
        Direction direction = mapper.directionDTOtoDirection(updatedInstanceRequest);
        direction = directionService.update(direction, id);
        return ResponseEntity.ok(mapper.directionToDirectionDTO(direction));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(directionService.delete(id));
    }
}

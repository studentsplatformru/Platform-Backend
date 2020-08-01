package ru.studentsplatform.backend.endpoint.rest.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.dto.university.DirectionDTO;
import ru.studentsplatform.backend.endpoint.mapper.DirectionMapper;
import ru.studentsplatform.backend.endpoint.rest.crud.DirectionController;
import ru.studentsplatform.backend.service.crud.DirectionService;
import ru.studentsplatform.backend.system.annotation.Profiled;

import java.util.List;

/**
 * @author Archie-Vian (sas-aramonov@yandex.ru) 01.08.2020
 */
@Profiled
@RestController
@RequestMapping(DirectionController.BASE_URL)
public class DirectionControllerImpl implements DirectionController {

    private final DirectionMapper mapper;
    private final DirectionService service;

    /**
     * Конструктор.
     * @param mapper    маппер direction
     * @param service   CRUD сервис direction
     */
    public DirectionControllerImpl(DirectionMapper mapper, DirectionService service) {
        this.mapper = mapper;
        this.service = service;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<DirectionDTO> create(DirectionDTO directionDTO) {
        var entity = mapper.directionDTOToDirection(directionDTO);
        var result = mapper.directionToDirectionDTO(service.create(entity));
        return ResponseEntity.ok(result);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<DirectionDTO> getById(Long id) {
        var result = mapper.directionToDirectionDTO(service.getById(id));
        return ResponseEntity.ok(result);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<List<DirectionDTO>> getAll() {
        var result = mapper.listDirectionToDirectionDTO(service.getAll());
        return ResponseEntity.ok(result);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<DirectionDTO> update(DirectionDTO directionDTO, Long id) {
        var entity = mapper.directionDTOToDirection(directionDTO);
        var result = mapper.directionToDirectionDTO(service.update(entity, id));
        return ResponseEntity.ok(result);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}

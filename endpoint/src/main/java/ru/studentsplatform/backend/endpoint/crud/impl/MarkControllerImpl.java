package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.MarkDTO;
import ru.studentsplatform.backend.endpoint.crud.MarkController;
import ru.studentsplatform.backend.entities.model.Mark;
import ru.studentsplatform.backend.mapper.MarkMapper;
import ru.studentsplatform.backend.service.crud.MarkService;

import java.util.List;

@RestController
@RequestMapping(MarkController.BASE_URL)
public class MarkControllerImpl implements MarkController {

    private final MarkService markService;

    private final MarkMapper mapper;

    public MarkControllerImpl(MarkService markService, MarkMapper mapper) {
        this.markService = markService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<MarkDTO> create(MarkDTO newInstanceRequest) {
        Mark mark = mapper.markDTOtoMark(newInstanceRequest);
        mark = markService.create(mark);
        return ResponseEntity.ok(mapper.markToMarkDTO(mark));
    }

    @Override
    public ResponseEntity<MarkDTO> getById(Long id) {
        Mark mark = markService.getById(id);
        MarkDTO markDTO = mapper.markToMarkDTO(mark);
        return ResponseEntity.ok(markDTO);
    }

    @Override
    public ResponseEntity<List<MarkDTO>> getAll() {
        List<Mark> markList = markService.getAll();
        List<MarkDTO> markDTOList = mapper.listMarkToMarkDTO(markList);
        return ResponseEntity.ok(markDTOList);
    }

    @Override
    public ResponseEntity<MarkDTO> update(MarkDTO updatedInstanceRequest, Long id) {
        Mark mark = mapper.markDTOtoMark(updatedInstanceRequest);
        mark = markService.update(mark, id);
        return ResponseEntity.ok(mapper.markToMarkDTO(mark));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(markService.delete(id));
    }
}

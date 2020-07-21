package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.LessonUnitDTO;
import ru.studentsplatform.backend.endpoint.crud.LessonUnitController;
import ru.studentsplatform.backend.entities.model.LessonUnit;
import ru.studentsplatform.backend.mapper.LessonUnitMapper;
import ru.studentsplatform.backend.service.crud.LessonUnitService;

import java.util.List;

@RestController
@RequestMapping(LessonUnitController.BASE_URL)
public class LessonUnitControllerImpl implements LessonUnitController {

    private final LessonUnitService lessonUnitService;

    private final LessonUnitMapper mapper;

    public LessonUnitControllerImpl(LessonUnitService lessonUnitService, LessonUnitMapper mapper) {
        this.lessonUnitService = lessonUnitService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<LessonUnitDTO> create(LessonUnitDTO newInstanceRequest) {
        LessonUnit lessonUnit = mapper.lessonUnitDTOToLessonUnit(newInstanceRequest);
        lessonUnit = lessonUnitService.create(lessonUnit);
        return ResponseEntity.ok(mapper.lessonUnitToLessonUnitDTO(lessonUnit));
    }

    @Override
    public ResponseEntity<LessonUnitDTO> getById(Long id) {
        LessonUnit lessonUnit = lessonUnitService.getById(id);
        LessonUnitDTO lessonUnitDTO = mapper.lessonUnitToLessonUnitDTO(lessonUnit);
        return ResponseEntity.ok(lessonUnitDTO);
    }

    @Override
    public ResponseEntity<List<LessonUnitDTO>> getAll() {
        List<LessonUnit> lessonUnitList = lessonUnitService.getAll();
        List<LessonUnitDTO> lessonUnitDTOList = mapper.listLessonUnitToLessonUnitDTO(lessonUnitList);
        return ResponseEntity.ok(lessonUnitDTOList);
    }

    @Override
    public ResponseEntity<LessonUnitDTO> update(LessonUnitDTO updatedInstanceRequest, Long id) {
        LessonUnit lessonUnit = mapper.lessonUnitDTOToLessonUnit(updatedInstanceRequest);
        lessonUnit = lessonUnitService.update(lessonUnit, id);
        return ResponseEntity.ok(mapper.lessonUnitToLessonUnitDTO(lessonUnit));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(lessonUnitService.delete(id));
    }
}

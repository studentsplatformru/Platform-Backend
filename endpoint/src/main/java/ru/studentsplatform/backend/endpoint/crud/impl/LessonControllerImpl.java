package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.LessonDTO;
import ru.studentsplatform.backend.endpoint.crud.LessonController;
import ru.studentsplatform.backend.entities.model.Lesson;
import ru.studentsplatform.backend.mapper.LessonMapper;
import ru.studentsplatform.backend.service.crud.LessonService;

import java.util.List;

@RestController
@RequestMapping(LessonController.BASE_URL)
public class LessonControllerImpl implements LessonController {

    private final LessonService lessonService;

    private final LessonMapper mapper;

    public LessonControllerImpl(LessonService lessonService, LessonMapper mapper) {
        this.lessonService = lessonService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<LessonDTO> create(LessonDTO newInstanceRequest) {
        Lesson lesson = mapper.lessonDTOtoLesson(newInstanceRequest);
        lesson = lessonService.create(lesson);
        return ResponseEntity.ok(mapper.lessonToLessonDTO(lesson));
    }

    @Override
    public ResponseEntity<LessonDTO> getById(Long id) {
        Lesson lesson = lessonService.getById(id);
        LessonDTO lessonDTO = mapper.lessonToLessonDTO(lesson);
        return ResponseEntity.ok(lessonDTO);
    }

    @Override
    public ResponseEntity<List<LessonDTO>> getAll() {
        List<Lesson> lessonList = lessonService.getAll();
        List<LessonDTO> lessonDTOList = mapper.listLessonToLessonDTO(lessonList);
        return ResponseEntity.ok(lessonDTOList);
    }

    @Override
    public ResponseEntity<LessonDTO> update(LessonDTO updatedInstanceRequest, Long id) {
        Lesson lesson = mapper.lessonDTOtoLesson(updatedInstanceRequest);
        lesson = lessonService.update(lesson, id);
        return ResponseEntity.ok(mapper.lessonToLessonDTO(lesson));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(lessonService.delete(id));
    }
}

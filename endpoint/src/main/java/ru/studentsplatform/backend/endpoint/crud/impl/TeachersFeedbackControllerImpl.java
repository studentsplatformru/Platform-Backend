package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.TeachersFeedbackDTO;
import ru.studentsplatform.backend.endpoint.crud.TeachersFeedbackController;
import ru.studentsplatform.backend.entities.model.TeachersFeedback;
import ru.studentsplatform.backend.mapper.TeachersFeedbackMapper;
import ru.studentsplatform.backend.service.crud.TeachersFeedbackService;

import java.util.List;

@RestController
@RequestMapping(TeachersFeedbackController.BASE_URL)
public class TeachersFeedbackControllerImpl implements TeachersFeedbackController {
    private final TeachersFeedbackService teachersFeedbackService;

    private final TeachersFeedbackMapper mapper;

    public TeachersFeedbackControllerImpl(TeachersFeedbackService teachersFeedbackService,
                                          TeachersFeedbackMapper mapper) {
        this.teachersFeedbackService = teachersFeedbackService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<TeachersFeedbackDTO> create(TeachersFeedbackDTO newInstanceRequest) {
        TeachersFeedback teachersFeedback = mapper.teachersFeedbackDTOtoTeachersFeedback(newInstanceRequest);
        teachersFeedback = teachersFeedbackService.create(teachersFeedback);
        return ResponseEntity.ok(mapper.teachersFeedbackToTeachersFeedbackDTO(teachersFeedback));
    }

    @Override
    public ResponseEntity<TeachersFeedbackDTO> getById(Long id) {
        TeachersFeedback teachersFeedback = teachersFeedbackService.getById(id);
        TeachersFeedbackDTO teachersFeedbackDTO = mapper.teachersFeedbackToTeachersFeedbackDTO(teachersFeedback);
        return ResponseEntity.ok(teachersFeedbackDTO);
    }

    @Override
    public ResponseEntity<List<TeachersFeedbackDTO>> getAll() {
        List<TeachersFeedback> teachersFeedbackList = teachersFeedbackService.getAll();
        List<TeachersFeedbackDTO> teachersFeedbackDTOList =
                mapper.listTeachersFeedbackToTeachersFeedbackDTO(teachersFeedbackList);
        return ResponseEntity.ok(teachersFeedbackDTOList);
    }

    @Override
    public ResponseEntity<TeachersFeedbackDTO> update(TeachersFeedbackDTO updatedInstanceRequest, Long id) {
        TeachersFeedback teachersFeedback = mapper.teachersFeedbackDTOtoTeachersFeedback(updatedInstanceRequest);
        teachersFeedback = teachersFeedbackService.update(teachersFeedback, id);
        return ResponseEntity.ok(mapper.teachersFeedbackToTeachersFeedbackDTO(teachersFeedback));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(teachersFeedbackService.delete(id));
    }
}

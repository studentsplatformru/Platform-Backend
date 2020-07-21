package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.TeacherDTO;
import ru.studentsplatform.backend.endpoint.crud.TeacherController;
import ru.studentsplatform.backend.entities.model.Teacher;
import ru.studentsplatform.backend.mapper.TeacherMapper;
import ru.studentsplatform.backend.service.crud.TeacherService;

import java.util.List;

@RestController
@RequestMapping(TeacherController.BASE_URL)
public class TeacherControllerImpl implements TeacherController {
    private final TeacherService teacherService;

    private final TeacherMapper mapper;

    public TeacherControllerImpl(TeacherService teacherService, TeacherMapper mapper) {
        this.teacherService = teacherService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<TeacherDTO> create(TeacherDTO newInstanceRequest) {
        Teacher teacher = mapper.teacherDTOtoTeacher(newInstanceRequest);
        teacher = teacherService.create(teacher);
        return ResponseEntity.ok(mapper.teacherToTeacherDTO(teacher));
    }

    @Override
    public ResponseEntity<TeacherDTO> getById(Long id) {
        Teacher teacher = teacherService.getById(id);
        TeacherDTO teacherDTO = mapper.teacherToTeacherDTO(teacher);
        return ResponseEntity.ok(teacherDTO);
    }

    @Override
    public ResponseEntity<List<TeacherDTO>> getAll() {
        List<Teacher> teacherList = teacherService.getAll();
        List<TeacherDTO> teacherDTOList = mapper.listTeacherToTeacherDTO(teacherList);
        return ResponseEntity.ok(teacherDTOList);
    }

    @Override
    public ResponseEntity<TeacherDTO> update(TeacherDTO updatedInstanceRequest, Long id) {
        Teacher teacher = mapper.teacherDTOtoTeacher(updatedInstanceRequest);
        teacher = teacherService.update(teacher, id);
        return ResponseEntity.ok(mapper.teacherToTeacherDTO(teacher));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(teacherService.delete(id));
    }
}

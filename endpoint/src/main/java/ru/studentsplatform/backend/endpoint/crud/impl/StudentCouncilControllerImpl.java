package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.StudentCouncilDTO;
import ru.studentsplatform.backend.endpoint.crud.StudentCouncilController;
import ru.studentsplatform.backend.entities.model.StudentCouncil;
import ru.studentsplatform.backend.mapper.StudentCouncilMapper;
import ru.studentsplatform.backend.service.crud.StudentCouncilService;

import java.util.List;

@RestController
@RequestMapping(StudentCouncilController.BASE_URL)
public class StudentCouncilControllerImpl implements StudentCouncilController {
    private final StudentCouncilService studentCouncilService;

    private final StudentCouncilMapper mapper;

    public StudentCouncilControllerImpl(StudentCouncilService studentCouncilService, StudentCouncilMapper mapper) {
        this.studentCouncilService = studentCouncilService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<StudentCouncilDTO> create(StudentCouncilDTO newInstanceRequest) {
        StudentCouncil studentCouncil = mapper.studentCouncilDTOtoStudentCouncil(newInstanceRequest);
        studentCouncil = studentCouncilService.create(studentCouncil);
        return ResponseEntity.ok(mapper.studentCouncilToStudentCouncilDTO(studentCouncil));
    }

    @Override
    public ResponseEntity<StudentCouncilDTO> getById(Long id) {
        StudentCouncil studentCouncil = studentCouncilService.getById(id);
        StudentCouncilDTO studentCouncilDTO = mapper.studentCouncilToStudentCouncilDTO(studentCouncil);
        return ResponseEntity.ok(studentCouncilDTO);
    }

    @Override
    public ResponseEntity<List<StudentCouncilDTO>> getAll() {
        List<StudentCouncil> studentCouncilList = studentCouncilService.getAll();
        List<StudentCouncilDTO> studentCouncilDTOList =
                mapper.listStudentCouncilToStudentCouncilDTO(studentCouncilList);
        return ResponseEntity.ok(studentCouncilDTOList);
    }

    @Override
    public ResponseEntity<StudentCouncilDTO> update(StudentCouncilDTO updatedInstanceRequest, Long id) {
        StudentCouncil studentCouncil = mapper.studentCouncilDTOtoStudentCouncil(updatedInstanceRequest);
        studentCouncil = studentCouncilService.update(studentCouncil, id);
        return ResponseEntity.ok(mapper.studentCouncilToStudentCouncilDTO(studentCouncil));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(studentCouncilService.delete(id));
    }
}

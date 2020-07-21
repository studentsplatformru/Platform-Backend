package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.StudentDTO;
import ru.studentsplatform.backend.endpoint.crud.StudentController;
import ru.studentsplatform.backend.entities.model.Student;
import ru.studentsplatform.backend.mapper.StudentMapper;
import ru.studentsplatform.backend.service.crud.StudentService;

import java.util.List;

@RestController
@RequestMapping(StudentController.BASE_URL)
public class StudentControllerImpl implements StudentController {
    private final StudentService studentService;

    private final StudentMapper mapper;

    public StudentControllerImpl(StudentService studentService, StudentMapper mapper) {
        this.studentService = studentService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<StudentDTO> create(StudentDTO newInstanceRequest) {
        Student student = mapper.studentDTOtoStudent(newInstanceRequest);
        student = studentService.create(student);
        return ResponseEntity.ok(mapper.studentToStudentDTO(student));
    }

    @Override
    public ResponseEntity<StudentDTO> getById(Long id) {
        Student student = studentService.getById(id);
        StudentDTO studentDTO = mapper.studentToStudentDTO(student);
        return ResponseEntity.ok(studentDTO);
    }

    @Override
    public ResponseEntity<List<StudentDTO>> getAll() {
        List<Student> studentList = studentService.getAll();
        List<StudentDTO> studentDTOList = mapper.listStudentToStudentDTO(studentList);
        return ResponseEntity.ok(studentDTOList);
    }

    @Override
    public ResponseEntity<StudentDTO> update(StudentDTO updatedInstanceRequest, Long id) {
        Student student = mapper.studentDTOtoStudent(updatedInstanceRequest);
        student = studentService.update(student, id);
        return ResponseEntity.ok(mapper.studentToStudentDTO(student));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(studentService.delete(id));
    }
}

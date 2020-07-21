package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.SubjectDTO;
import ru.studentsplatform.backend.endpoint.crud.SubjectController;
import ru.studentsplatform.backend.entities.model.Subject;
import ru.studentsplatform.backend.mapper.SubjectMapper;
import ru.studentsplatform.backend.service.crud.SubjectService;

import java.util.List;

@RestController
@RequestMapping(SubjectController.BASE_URL)
public class SubjectControllerImpl implements SubjectController {
    private final SubjectService subjectService;

    private final SubjectMapper mapper;

    public SubjectControllerImpl(SubjectService subjectService, SubjectMapper mapper) {
        this.subjectService = subjectService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<SubjectDTO> create(SubjectDTO newInstanceRequest) {
        Subject subject = mapper.subjectDTOtoSubject(newInstanceRequest);
        subject = subjectService.create(subject);
        return ResponseEntity.ok(mapper.subjectToSubjectDTO(subject));
    }

    @Override
    public ResponseEntity<SubjectDTO> getById(Long id) {
        Subject subject = subjectService.getById(id);
        SubjectDTO subjectDTO = mapper.subjectToSubjectDTO(subject);
        return ResponseEntity.ok(subjectDTO);
    }

    @Override
    public ResponseEntity<List<SubjectDTO>> getAll() {
        List<Subject> subjectList = subjectService.getAll();
        List<SubjectDTO> subjectDTOList = mapper.listSubjectToSubjectDTO(subjectList);
        return ResponseEntity.ok(subjectDTOList);
    }

    @Override
    public ResponseEntity<SubjectDTO> update(SubjectDTO updatedInstanceRequest, Long id) {
        Subject subject = mapper.subjectDTOtoSubject(updatedInstanceRequest);
        subject = subjectService.update(subject, id);
        return ResponseEntity.ok(mapper.subjectToSubjectDTO(subject));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(subjectService.delete(id));
    }
}

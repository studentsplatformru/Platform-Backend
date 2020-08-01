package ru.studentsplatform.backend.endpoint.rest.crud;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.dto.university.SubjectDTO;
import ru.studentsplatform.backend.endpoint.mapper.SubjectMapper;
import ru.studentsplatform.backend.endpoint.rest.SubjectController;
import ru.studentsplatform.backend.service.crud.impl.SubjectServiceImpl;
import ru.studentsplatform.backend.system.annotation.Profiled;

import java.util.List;

@Profiled
@RestController
@RequestMapping(SubjectController.BASE_URL)
public class SubjectControllerImpl implements SubjectController {
    private final SubjectMapper subjectMapper;

    private final SubjectServiceImpl subjectService;

    /**
     * Конструктор.
     * @param subjectMapper маппер, преобразующий SubjectDTO в сущность Subject и наоборот.
     * @param subjectService CRUD сервис Subject.
     */
    public SubjectControllerImpl(SubjectMapper subjectMapper,
                                          SubjectServiceImpl subjectService) {
        this.subjectMapper = subjectMapper;
        this.subjectService = subjectService;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<SubjectDTO> create(SubjectDTO dto) {
        var subject = subjectMapper.subjectDTOToSubject(dto);
        subject = subjectService.create(subject);
        var result = subjectMapper.subjectToSubjectDTO(subject);
        return ResponseEntity.ok(result);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<SubjectDTO> getById(Long id) {
        return ResponseEntity.ok(subjectMapper.subjectToSubjectDTO(subjectService.getById(id)));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<List<SubjectDTO>> getAll() {
        return ResponseEntity.ok(subjectMapper.listSubjectToSubjectDTO(subjectService.getAll()));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<SubjectDTO> update(SubjectDTO dto, Long id) {
        var entity = subjectMapper.subjectDTOToSubject(dto);
        return ResponseEntity.ok(subjectMapper
                .subjectToSubjectDTO(subjectService.update(entity, id)));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(subjectService.delete(id));
    }
}


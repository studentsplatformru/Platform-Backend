package ru.studentsplatform.backend.endpoint.rest.crud.impl;

import org.springframework.http.ResponseEntity;
import ru.studentsplatform.backend.domain.dto.university.FacultyDTO;
import ru.studentsplatform.backend.endpoint.mapper.FacultyMapper;
import ru.studentsplatform.backend.endpoint.rest.crud.FacultyController;
import ru.studentsplatform.backend.service.crud.FacultyService;

import java.util.List;

/**
 * @author Archie-Vian (sas-aramonov@yandex.ru) 01.08.2020
 */
public class FacultyControllerImpl implements FacultyController {

    private final FacultyMapper mapper;
    private final FacultyService service;

    /**
     * Конструктор.
     * @param mapper Маппер faculty
     * @param service CRUD сервис faculty
     */
    public FacultyControllerImpl(FacultyMapper mapper, FacultyService service) {
        this.mapper = mapper;
        this.service = service;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<FacultyDTO> create(FacultyDTO facultyDTO) {
        var entity = mapper.facultyDTOToFaculty(facultyDTO);
        var result = mapper.facultyToFacultyDTO(service.create(entity));
        return ResponseEntity.ok(result);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<FacultyDTO> getById(Long id) {
        var result = mapper.facultyToFacultyDTO(service.getById(id));
        return ResponseEntity.ok(result);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<List<FacultyDTO>> getAll() {
        var result = mapper.listFacultyToFacultyDTO(service.getAll());
        return ResponseEntity.ok(result);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<FacultyDTO> update(FacultyDTO facultyDTO, Long id) {
        var entity = mapper.facultyDTOToFaculty(facultyDTO);
        var result = mapper.facultyToFacultyDTO(service.update(entity, id));
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

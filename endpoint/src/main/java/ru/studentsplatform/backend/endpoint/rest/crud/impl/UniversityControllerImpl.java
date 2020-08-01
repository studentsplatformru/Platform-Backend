package ru.studentsplatform.backend.endpoint.rest.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.dto.university.UniversityDTO;
import ru.studentsplatform.backend.endpoint.mapper.UniversityMapper;
import ru.studentsplatform.backend.endpoint.rest.crud.UniversityController;
import ru.studentsplatform.backend.service.crud.impl.UniversityServiceImpl;
import ru.studentsplatform.backend.system.annotation.Profiled;

import java.util.List;

@Profiled
@RestController
@RequestMapping(UniversityController.BASE_URL)
public class UniversityControllerImpl implements UniversityController {
    private final UniversityMapper universityMapper;

    private final UniversityServiceImpl universityService;

    /**
     * Конструктор.
     * @param universityMapper маппер, преобразующий UniversityDTO в сущность University и наоборот.
     * @param universityService CRUD сервис University.
     */
    public UniversityControllerImpl(UniversityMapper universityMapper,
                              UniversityServiceImpl universityService) {
        this.universityMapper = universityMapper;
        this.universityService = universityService;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<UniversityDTO> create(UniversityDTO dto) {
        var university = universityMapper.universityDTOToUniversity(dto);
        university = universityService.create(university);
        var result = universityMapper.universityToUniversityDTO(university);
        return ResponseEntity.ok(result);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<UniversityDTO> getById(Long id) {
        return ResponseEntity.ok(universityMapper.universityToUniversityDTO(universityService.getById(id)));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<List<UniversityDTO>> getAll() {
        return ResponseEntity.ok(universityMapper.listUniversityToUniversityDTO(universityService.getAll()));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<UniversityDTO> update(UniversityDTO dto, Long id) {
        var entity = universityMapper.universityDTOToUniversity(dto);
        return ResponseEntity.ok(universityMapper
                .universityToUniversityDTO(universityService.update(entity, id)));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(universityService.delete(id));
    }
}

package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.UniversityDTO;
import ru.studentsplatform.backend.endpoint.crud.UniversityController;
import ru.studentsplatform.backend.entities.model.University;
import ru.studentsplatform.backend.mapper.UniversityMapper;
import ru.studentsplatform.backend.service.crud.UniversityService;

import java.util.List;

@RestController
@RequestMapping(UniversityController.BASE_URL)
public class UniversityControllerImpl implements UniversityController {
    private final UniversityService universityService;

    private final UniversityMapper mapper;

    public UniversityControllerImpl(UniversityService universityService, UniversityMapper mapper) {
        this.universityService = universityService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<UniversityDTO> create(UniversityDTO newInstanceRequest) {
        University university = mapper.universityDTOtoUniversity(newInstanceRequest);
        university = universityService.create(university);
        return ResponseEntity.ok(mapper.universityToUniversityDTO(university));
    }

    @Override
    public ResponseEntity<UniversityDTO> getById(Long id) {
        University university = universityService.getById(id);
        UniversityDTO universityDTO = mapper.universityToUniversityDTO(university);
        return ResponseEntity.ok(universityDTO);
    }

    @Override
    public ResponseEntity<List<UniversityDTO>> getAll() {
        List<University> universityList = universityService.getAll();
        List<UniversityDTO> universityDTOList = mapper.listUniversityToUniversityDTO(universityList);
        return ResponseEntity.ok(universityDTOList);
    }

    @Override
    public ResponseEntity<UniversityDTO> update(UniversityDTO updatedInstanceRequest, Long id) {
        University university = mapper.universityDTOtoUniversity(updatedInstanceRequest);
        university = universityService.update(university, id);
        return ResponseEntity.ok(mapper.universityToUniversityDTO(university));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(universityService.delete(id));
    }
}

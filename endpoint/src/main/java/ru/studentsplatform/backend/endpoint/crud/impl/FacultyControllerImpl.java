package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.FacultyDTO;
import ru.studentsplatform.backend.endpoint.crud.DepartmentController;
import ru.studentsplatform.backend.endpoint.crud.FacultyController;
import ru.studentsplatform.backend.entities.model.Faculty;
import ru.studentsplatform.backend.mapper.FacultyMapper;
import ru.studentsplatform.backend.service.crud.FacultyService;
import java.util.List;

@RestController
@RequestMapping(DepartmentController.BASE_URL)
public class FacultyControllerImpl implements FacultyController {

    private final FacultyService facultyService;

    private final FacultyMapper mapper;

    public FacultyControllerImpl(FacultyService facultyService, FacultyMapper mapper) {
        this.facultyService = facultyService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<FacultyDTO> create(FacultyDTO newInstanceRequest) {
        Faculty faculty = mapper.facultyDTOtoFaculty(newInstanceRequest);
        faculty = facultyService.create(faculty);
        return ResponseEntity.ok(mapper.facultyToFacultyDTO(faculty));
    }

    @Override
    public ResponseEntity<FacultyDTO> getById(Long id) {
        Faculty faculty = facultyService.getById(id);
        FacultyDTO facultyDTO = mapper.facultyToFacultyDTO(faculty);
        return ResponseEntity.ok(facultyDTO);
    }

    @Override
    public ResponseEntity<List<FacultyDTO>> getAll() {
        List<Faculty> facultyList = facultyService.getAll();
        List<FacultyDTO> facultyDTOList = mapper.listFacultyToFacultyDTO(facultyList);
        return ResponseEntity.ok(facultyDTOList);
    }

    @Override
    public ResponseEntity<FacultyDTO> update(FacultyDTO updatedInstanceRequest, Long id) {
        Faculty faculty = mapper.facultyDTOtoFaculty(updatedInstanceRequest);
        faculty = facultyService.update(faculty, id);
        return ResponseEntity.ok(mapper.facultyToFacultyDTO(faculty));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(facultyService.delete(id));
    }
}

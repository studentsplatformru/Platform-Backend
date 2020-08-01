package ru.studentsplatform.backend.domain.dto.university;

import lombok.Data;
import ru.studentsplatform.backend.domain.dto.BaseDTO;

import java.util.List;

@Data
public class FacultyDTO extends BaseDTO {

    private String faculty;
    private Long universityId;
    private List<DepartmentDTO> departments;
    private List<DirectionDTO> directions;

}

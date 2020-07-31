package ru.studentsplatform.backend.domain.dto.university;

import lombok.Data;
import ru.studentsplatform.backend.domain.dto.BaseDTO;
import ru.studentsplatform.backend.entities.model.enums.UniversityEnum;

import java.util.List;

@Data
public class UniversityDTO extends BaseDTO {

    private String university;
    private List<FacultyDTO> faculties;

}

package ru.studentsplatform.backend.domain.dto.university;

import lombok.Data;
import ru.studentsplatform.backend.domain.dto.BaseDTO;

@Data
public class PlaceStudyDTO extends BaseDTO {

    private Long UserId;
    private Long universityId;
    private Long facultyId;
    private Long departmentId;
    private Long directionId;
    private Long teamId;
    private Integer semester;

}

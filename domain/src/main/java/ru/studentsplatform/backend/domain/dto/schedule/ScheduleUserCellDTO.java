package ru.studentsplatform.backend.domain.dto.schedule;

import lombok.Data;
import ru.studentsplatform.backend.domain.dto.university.TaskDTO;
import ru.studentsplatform.backend.entities.model.enums.UniversityRoleEnum;

import java.util.List;

@Data
public class ScheduleUserCellDTO {

    private Long userId;
    private Long scheduleCellId;
    private UniversityRoleEnum universityRole;
    private List<TaskDTO> taskDTOS;

}

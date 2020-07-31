package ru.studentsplatform.backend.domain.dto.schedule;

import lombok.Data;
import ru.studentsplatform.backend.domain.dto.BaseDTO;
import ru.studentsplatform.backend.domain.dto.university.TaskDTO;


import java.util.List;

@Data
public class ScheduleUserCellDTO extends BaseDTO {

    private Long userId;
    private Long scheduleCellId;
    private String universityRole;
    private List<TaskDTO> tasks;

}

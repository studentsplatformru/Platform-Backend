package ru.studentsplatform.backend.domain.dto.university;

import lombok.Data;
import ru.studentsplatform.backend.domain.dto.BaseDTO;
import ru.studentsplatform.backend.domain.dto.schedule.ScheduleUserCellDTO;

import java.util.List;

@Data
public class DisciplineDTO extends BaseDTO {

    private Long userId;
    private Long subjectId;
    private Integer semester;
    private List<ScheduleUserCellDTO> scheduleUserCells;

}

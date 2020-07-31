package ru.studentsplatform.backend.domain.dto.schedule;

import lombok.Data;
import ru.studentsplatform.backend.domain.dto.BaseDTO;
import ru.studentsplatform.backend.entities.model.enums.ClassTypeEnum;

import java.time.OffsetDateTime;

@Data
public class ScheduleCellDTO extends BaseDTO {

    private OffsetDateTime startClass;
    private OffsetDateTime endClass;
    private ClassTypeEnum type;
    private Long teamId;
    private Long subjectId;

}

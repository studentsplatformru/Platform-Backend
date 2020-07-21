package ru.studentsplatform.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.studentsplatform.backend.entities.model.Subject;
import ru.studentsplatform.backend.entities.model.Teacher;


import java.io.Serializable;
import java.time.OffsetTime;

@Data
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class LessonUnitDTO extends BaseDTO implements Serializable {

    private OffsetTime startTime;
    private OffsetTime endTime;
    private String audience;
    private String type;
    private String note;
    private Teacher teacher;
    private Subject subject;
}

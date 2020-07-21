package ru.studentsplatform.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.studentsplatform.backend.entities.model.*;

import java.io.Serializable;
import java.time.OffsetTime;
import java.util.List;

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
    private List<Lesson> lessons;
    private Teacher teacher;
    private Subject subject;
}
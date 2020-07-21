package ru.studentsplatform.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.studentsplatform.backend.entities.model.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class LessonDTO extends BaseDTO implements Serializable {

    private OffsetDateTime date;
    private List<Homework> homeworkList;
    private List<Mark> marks;
    private List<Attendance> attendanceList;
    private Team team;
    private LessonUnit lessonUnit;
}
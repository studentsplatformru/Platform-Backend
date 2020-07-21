package ru.studentsplatform.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.studentsplatform.backend.entities.model.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class TeacherDTO extends BaseDTO implements Serializable {

    private String personalPage;
    private User user;
    private Department department;
    private Set<TeachersFeedback> teachersFeedback;
    private List<LessonUnit> lessonUnits;
    private Direction direction;
}
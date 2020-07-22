package ru.studentsplatform.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class TeacherDTO extends BaseDTO implements Serializable {

    private Long userId;
    private String personalPage;
    private Long departmentId;
    private Long directionId;

    private Set<TeachersFeedbackDTO> teachersFeedbackDTO;
    private List<LessonUnitDTO> lessonUnitsDTO;
}
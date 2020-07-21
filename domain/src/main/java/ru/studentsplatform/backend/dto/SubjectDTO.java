package ru.studentsplatform.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.studentsplatform.backend.entities.model.*;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class SubjectDTO extends BaseDTO implements Serializable {

    private String subjectName;
    private Team team;
    private List<LessonUnit> lessonUnits;
    private List<Material> materials;
}
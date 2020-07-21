package ru.studentsplatform.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.studentsplatform.backend.entities.model.LessonUnit;
import ru.studentsplatform.backend.entities.model.Material;
import ru.studentsplatform.backend.entities.model.Team;


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
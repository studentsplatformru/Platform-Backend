package ru.studentsplatform.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class SubjectDTO extends BaseDTO implements Serializable {

    private String subjectName;
    private Long teamId;

    private List<LessonUnitDTO> lessonUnitsDTO;
    private List<MaterialDTO> materialsDTO;
}
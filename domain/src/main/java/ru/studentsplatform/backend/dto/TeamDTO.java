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
public class TeamDTO extends BaseDTO implements Serializable {

    private int course;
    private int teamName;
    private Long directionId;

    private List<StudentDTO> studentsDTO;
    private List<LessonDTO> lessonsDTO;
    private List<SubjectDTO> subjectsDTO;
}
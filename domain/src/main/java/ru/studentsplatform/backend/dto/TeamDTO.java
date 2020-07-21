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
public class TeamDTO extends BaseDTO implements Serializable {

    private int course;
    private int teamName;
    private List<Student> students;
    private List<Lesson> lessons;
    private List<Subject> subjects;
    private Direction direction;
}
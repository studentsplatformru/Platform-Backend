package ru.studentsplatform.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.studentsplatform.backend.entities.model.Direction;
import ru.studentsplatform.backend.entities.model.Lesson;
import ru.studentsplatform.backend.entities.model.Student;
import ru.studentsplatform.backend.entities.model.Subject;


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
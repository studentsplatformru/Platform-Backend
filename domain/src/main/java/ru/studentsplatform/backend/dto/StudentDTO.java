package ru.studentsplatform.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.studentsplatform.backend.entities.model.Department;
import ru.studentsplatform.backend.entities.model.Direction;
import ru.studentsplatform.backend.entities.model.Team;
import ru.studentsplatform.backend.entities.model.User;
import ru.studentsplatform.backend.entities.model.Mark;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class StudentDTO extends BaseDTO implements Serializable {

    private User user;
    private Department department;
    private Team team;
    private Direction direction;
    private List<Mark> marks;
}
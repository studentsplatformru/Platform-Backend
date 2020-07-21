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
public class StudentDTO extends BaseDTO implements Serializable {

    private User user;
    private Department department;
    private Team team;
    private Direction direction;
    private List<Mark> marks;
}

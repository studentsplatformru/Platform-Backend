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

import java.io.Serializable;

@Data
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class StudentDTO extends BaseDTO implements Serializable {

    private Long departmentId;
    private Long teamId;
    private Long directionId;

}
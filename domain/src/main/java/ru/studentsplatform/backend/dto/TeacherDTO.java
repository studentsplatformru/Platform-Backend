package ru.studentsplatform.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.studentsplatform.backend.entities.model.Department;
import ru.studentsplatform.backend.entities.model.User;
import ru.studentsplatform.backend.entities.model.Direction;

import java.io.Serializable;

@Data
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class TeacherDTO extends BaseDTO implements Serializable {

    private String personalPage;
    private User user;
    private Department department;
    private Direction direction;
}
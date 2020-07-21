package ru.studentsplatform.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.studentsplatform.backend.entities.model.Faculty;
import ru.studentsplatform.backend.entities.model.Student;
import ru.studentsplatform.backend.entities.model.Teacher;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class DepartmentDTO extends BaseDTO implements Serializable {

    private String departmentName;
    private Faculty faculty;
    private List<Student> students;
    private List<Teacher> teachers;

}

package ru.studentsplatform.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.studentsplatform.backend.entities.model.Student;
import ru.studentsplatform.backend.entities.model.Teacher;
import ru.studentsplatform.backend.entities.model.TeachersFeedback;

import java.io.Serializable;
import java.util.Set;

@Data
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class UserDTO extends BaseDTO implements Serializable {

    private String firstName;
    private String lastName;
    private String patronymic;
    private String phoneNumber;
    private String email;
    private String password;
    private Teacher teacher;
    private Student student;
    private Set<TeachersFeedback> teachersFeedback;

}

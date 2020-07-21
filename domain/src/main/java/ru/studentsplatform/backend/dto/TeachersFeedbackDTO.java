package ru.studentsplatform.backend.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ru.studentsplatform.backend.entities.model.Teacher;
import ru.studentsplatform.backend.entities.model.User;


import java.io.Serializable;

@Data
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class TeachersFeedbackDTO extends BaseDTO implements Serializable {

    private String header;
    private String content;
    private User author;
    private Teacher teacher;
}
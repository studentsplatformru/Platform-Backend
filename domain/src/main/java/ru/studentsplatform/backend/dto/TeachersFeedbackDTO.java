package ru.studentsplatform.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.studentsplatform.backend.entities.model.*;

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

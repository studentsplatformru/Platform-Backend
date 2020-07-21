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
public class HomeworkDTO extends BaseDTO implements Serializable {

    private Lesson lesson;
    private String fileType;
    private Byte[] file;
    private String note;
}
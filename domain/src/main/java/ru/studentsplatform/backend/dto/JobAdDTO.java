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
public class JobAdDTO extends BaseDTO implements Serializable {

    private String category;
    private String jobName;
    private String description;
    private String link;
    private Faculty faculty;

}
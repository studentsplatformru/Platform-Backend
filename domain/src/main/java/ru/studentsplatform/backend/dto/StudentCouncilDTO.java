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
public class StudentCouncilDTO extends BaseDTO implements Serializable {

    private String phone;
    private String email;
    private String audience;
    private String vkGroup;
    private Faculty faculty;
}
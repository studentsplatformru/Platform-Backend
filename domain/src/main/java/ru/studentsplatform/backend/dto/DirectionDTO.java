package ru.studentsplatform.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.studentsplatform.backend.entities.model.Faculty;
import ru.studentsplatform.backend.entities.model.Team;

import java.io.Serializable;
import java.util.Set;

@Data
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class DirectionDTO extends BaseDTO implements Serializable {

    private String directionName;
    private String directionCode;
    private Faculty faculty;
    private Set<Team> teams;

}

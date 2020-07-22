package ru.studentsplatform.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class DirectionDTO extends BaseDTO implements Serializable {

    private String directionName;
    private String directionCode;
    private Long facultyId;

    private Set<TeamDTO> teamsDTO;

}

package ru.studentsplatform.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.studentsplatform.backend.entities.model.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class FacultyDTO extends BaseDTO implements Serializable {

    private String facultyName;
    private University university;
    private Set<Department> departments;
    private Set<StudentCouncil> studentCouncils;
    private List<JobAd> jobAds;
    private Set<Direction> directions;
}
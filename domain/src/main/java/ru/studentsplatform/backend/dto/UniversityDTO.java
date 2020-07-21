package ru.studentsplatform.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.studentsplatform.backend.entities.model.Faculty;
import ru.studentsplatform.backend.entities.model.Library;

import java.util.List;
import java.util.Set;

@Data
@SuperBuilder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class UniversityDTO {

    private String universityName;
    private Set<Faculty> faculties;
    private List<Library> libraries;

}

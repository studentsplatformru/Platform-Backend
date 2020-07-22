package ru.studentsplatform.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class UniversityDTO extends BaseDTO implements Serializable {

    private String universityName;

    private Set<FacultyDTO> facultiesDTO;
    private List<LibraryDTO> librariesDTO;

}

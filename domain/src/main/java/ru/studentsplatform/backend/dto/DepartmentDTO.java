package ru.studentsplatform.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class DepartmentDTO extends BaseDTO implements Serializable {

    private String departmentName;
    private Long facultyId;

    private List<StudentDTO> studentsDTO;
    private List<TeacherDTO> teachersDTO;

}

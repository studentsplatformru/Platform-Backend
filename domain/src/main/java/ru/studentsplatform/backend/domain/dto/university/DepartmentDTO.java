package ru.studentsplatform.backend.domain.dto.university;

import lombok.Data;
import ru.studentsplatform.backend.domain.dto.user.UserDTO;

import java.util.List;

@Data
public class DepartmentDTO {

    private String department;
    private Long facultyId;
    private List<UserDTO> users;

}

package ru.studentsplatform.backend.domain.dto.university;

import lombok.Data;
import ru.studentsplatform.backend.domain.dto.BaseDTO;
import ru.studentsplatform.backend.domain.dto.user.UserDTO;

import java.util.List;

@Data
public class TeamDTO extends BaseDTO {

    private Long semester;
    private String teamName;
    private List<UserDTO> users;
    private Long directionId;

}

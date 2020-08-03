package ru.studentsplatform.backend.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import ru.studentsplatform.backend.domain.dto.BaseDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO {
    private String email;

    private String password;
    // TODO:
    // team_id.
    // place_study list.
    // discipline list.
    // schedule_user_cell.
}

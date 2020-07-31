package ru.studentsplatform.backend.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.studentsplatform.backend.domain.dto.BaseDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO extends BaseDTO {
    private Long userId;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String phoneNumber;
}

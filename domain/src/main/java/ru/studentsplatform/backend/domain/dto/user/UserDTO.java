package ru.studentsplatform.backend.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.studentsplatform.backend.domain.dto.BaseDTO;
import ru.studentsplatform.backend.domain.dto.university.DisciplineDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO {
	private String email;
	private String password;
	private String universityRole;
	private Long placeStudyId;
	private Long userInfoId;
	private List<DisciplineDTO> disciplines;
}

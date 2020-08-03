package ru.studentsplatform.backend.domain.dto.university;

import lombok.Data;
import ru.studentsplatform.backend.domain.dto.BaseDTO;

@Data
public class DepartmentDTO extends BaseDTO {

	private String department;
	private Long facultyId;

}

package ru.studentsplatform.backend.domain.dto.university;

import lombok.Data;
import ru.studentsplatform.backend.domain.dto.BaseDTO;

import java.util.List;

@Data
public class DirectionDTO extends BaseDTO {
	private String direction;
	private String directionCode;
	private Long facultyId;
	private List<TeamDTO> teams;
}

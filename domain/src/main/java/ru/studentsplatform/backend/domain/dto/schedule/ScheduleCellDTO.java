package ru.studentsplatform.backend.domain.dto.schedule;

import lombok.Data;
import ru.studentsplatform.backend.domain.dto.BaseDTO;

import java.time.OffsetDateTime;

@Data
public class ScheduleCellDTO extends BaseDTO {

	private OffsetDateTime startClass;
	private OffsetDateTime endClass;
	private String type;
	private Long teamId;
	private Long subjectId;
	private Integer semester;
}

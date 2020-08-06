package ru.studentsplatform.backend.domain.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ScheduleUserCellFilterDTO {

	private Long subjectId;
	private Long disciplineId;
	private Long userId;
	private Long scheduleCellId;
	private Integer semester;
	private OffsetDateTime startTime;
	private OffsetDateTime endTime;
	private Boolean presence;
}

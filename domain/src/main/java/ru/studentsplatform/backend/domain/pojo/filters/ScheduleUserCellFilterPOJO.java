package ru.studentsplatform.backend.domain.pojo.filters;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ScheduleUserCellFilterPOJO {

	private Long subjectId;
	private Long disciplineId;
	private Long userId;
	private Long scheduleCellId;
	private Integer semester;
	private OffsetDateTime startTime;
	private OffsetDateTime endTime;
	private Boolean presence;
}

package ru.studentsplatform.backend.domain.pojo.filters;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class TaskFilterPOJO {
	private OffsetDateTime startTime;
	private OffsetDateTime endTime;
	private Long subjectId;
	private Long groupId;
	private Long userCellId;
	private Integer semester;
	private Long userId;
}

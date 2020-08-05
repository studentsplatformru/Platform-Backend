package ru.studentsplatform.backend.domain.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class TaskFilterDTO {
	private OffsetDateTime startTime;
	private OffsetDateTime endTime;
	private Long subjectId;
	private Long groupId;
	private Long userCellId;
	private Integer semestr;
	private Long userId;
}

package ru.studentsplatform.backend.domain.dto.spbu;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SpbuEventDTO {

	private String teamName;

	private LocalTime startTime;

	private LocalTime endTime;

	private LocalDate date;

	private String subject;

	private String location;

	private String educator;

}

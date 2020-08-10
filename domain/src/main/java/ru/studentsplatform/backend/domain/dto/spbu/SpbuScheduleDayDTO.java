package ru.studentsplatform.backend.domain.dto.spbu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SpbuScheduleDayDTO {

	@JsonProperty("DayString")
	private String datName;
	@JsonProperty("Day")
	private Date dayDate;

	@JsonProperty("DayStudyEvents")
	private List<SpbuEventDTO> events;

}

package ru.studentsplatform.backend.domain.dto.spbu;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class SpbuEventDTO {

	@JsonProperty("Subject")
	private String subject;

	@JsonProperty("DateWithTimeIntervalString")
	private String dayWithTimeInterval;

	@JsonProperty("TimeIntervalString")
	private String timeInterval;

	@JsonProperty("LocationsDisplayText")
	private String location;

	@JsonProperty("EducatorsDisplayText")
	private String educator;

	@JsonProperty("Start")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp startTime;

	@JsonProperty("End")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp endTime;

	private String spbuTeamName;

}

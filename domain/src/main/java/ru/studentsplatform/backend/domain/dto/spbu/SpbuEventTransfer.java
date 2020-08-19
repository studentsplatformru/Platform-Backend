package ru.studentsplatform.backend.domain.dto.spbu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpbuEventTransfer {

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
	private String startTime;

	@JsonProperty("End")
	private String endTime;

	private String spbuTeamName;

}

package ru.studentsplatform.backend.domain.dto.spbu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpbuStudyProgramDTO {

	@JsonProperty("StudyProgramId")
	private String programId;
	@JsonProperty("YearNumber")
	private Integer year;
	@JsonProperty("PublicDivisionAlias")
	private String alias;

}

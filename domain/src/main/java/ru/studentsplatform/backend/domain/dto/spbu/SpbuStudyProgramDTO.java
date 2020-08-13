package ru.studentsplatform.backend.domain.dto.spbu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpbuStudyProgramDTO {

	@JsonProperty("StudyProgramId")
	private Long programId;

	@JsonProperty("PublicDivisionAlias")
	private String alias;

}

package ru.studentsplatform.backend.domain.dto.spbu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProgramLevelDTO {
	@JsonProperty("StudyLevelName")
	private String programLevel;
	@JsonProperty("StudyProgramCombinations")
	private List<ProgramCombination> programCombinations;

	private static class ProgramCombination {
		@JsonProperty("Name")
		private String combinationName;
		@JsonProperty("AdmissionYears")
		private List<Group> admissionYears;

		private static class Group {
			@JsonProperty("StudyProgramId")
			private String programId;
			@JsonProperty("YearNumber")
			private Integer year;
			@JsonProperty("PublicDivisionAlias")
			private String alias;
		}
	}
}





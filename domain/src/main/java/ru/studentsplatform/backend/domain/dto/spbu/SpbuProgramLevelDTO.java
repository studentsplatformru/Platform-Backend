package ru.studentsplatform.backend.domain.dto.spbu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SpbuProgramLevelDTO {
	@JsonProperty("StudyLevelName")
	private String programLevel;
	@JsonProperty("StudyProgramCombinations")
	private List<SpbuProgramCombinationDTO> programCombinations;


}





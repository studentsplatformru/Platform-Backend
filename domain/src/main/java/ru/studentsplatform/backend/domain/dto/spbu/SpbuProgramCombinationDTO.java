package ru.studentsplatform.backend.domain.dto.spbu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SpbuProgramCombinationDTO {

	@JsonProperty("Name")
	private String combinationName;

	@JsonProperty("AdmissionYears")
	private List<SpbuStudyProgramDTO> studyPrograms;

}

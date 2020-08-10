package ru.studentsplatform.backend.domain.dto.spbu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpbuGroupDTO {

	@JsonProperty("StudentGroupId")
	private Long id;
	@JsonProperty("StudentGroupName")
	private String name;

}

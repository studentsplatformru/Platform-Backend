package ru.studentsplatform.backend.domain.dto.spbu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SpbuGroupWrapperDTO {

	@JsonProperty("Groups")
	private List<SpbuGroupDTO> groups;

}

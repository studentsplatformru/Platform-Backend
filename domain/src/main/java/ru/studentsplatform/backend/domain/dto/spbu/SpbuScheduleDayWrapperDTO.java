package ru.studentsplatform.backend.domain.dto.spbu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.LinkedList;

@Data
public class SpbuScheduleDayWrapperDTO {

	@JsonProperty("Days")
	private LinkedList<SpbuScheduleDayDTO> days;

}

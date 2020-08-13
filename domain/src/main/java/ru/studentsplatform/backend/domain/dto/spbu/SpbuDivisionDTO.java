package ru.studentsplatform.backend.domain.dto.spbu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SpbuDivisionDTO {

	@JsonProperty("Oid")
	private String oid;

	@JsonProperty("Alias")
	private String alias;

	@JsonProperty("Name")
	private String name;

}

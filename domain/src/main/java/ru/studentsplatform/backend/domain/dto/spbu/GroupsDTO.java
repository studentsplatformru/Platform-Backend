package ru.studentsplatform.backend.domain.dto.spbu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GroupsDTO {
	@JsonProperty("Groups")
	private List<GroupDTO> groups;

	public static class GroupDTO {
		@JsonProperty("StudentGroupId")
		private Integer id;
		@JsonProperty("StudentGroupName")
		private String name;
	}
}

package ru.studentsplatform.backend.endpoint.mapper.spbu;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuTeamDTO;
import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpbuTeamMapper {

	@Mappings({
			@Mapping(target = "id", source = "entity.id"),
			@Mapping(target = "name", source = "entity.name"),
			@Mapping(target = "alias", source = "entity.alias")
	})
	SpbuTeamDTO spbuTeamToTeamDTO(SpbuTeam entity);

	@Mappings({
			@Mapping(target = "id", source = "dto.id"),
			@Mapping(target = "name", source = "dto.name"),
			@Mapping(target = "alias", source = "dto.alias")
	})
	SpbuTeam spbuTeamDTOToSpbuTeam(SpbuTeamDTO dto);

	List<SpbuTeamDTO> listSpbuTeamToSpbuTeamDTO(List<SpbuTeam> entity);

	List<SpbuTeam> listSpbuTeamDTOToSpbuTeam(List<SpbuTeamDTO> dto);
}

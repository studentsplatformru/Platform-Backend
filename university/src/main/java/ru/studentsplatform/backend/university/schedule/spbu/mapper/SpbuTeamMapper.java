package ru.studentsplatform.backend.university.schedule.spbu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuTeamDTO;
import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpbuTeamMapper {

	/**
	 * Создаёт DTO на основе сущности SpbuTeam.
	 *
	 * @param entity объект студенческой группы СПБГУ
	 * @return DTO студенческой группы СПБГУ
	 */
	@Mappings({
			@Mapping(target = "id", source = "entity.id"),
			@Mapping(target = "name", source = "entity.name"),
			@Mapping(target = "alias", source = "entity.alias")
	})
	SpbuTeamDTO spbuTeamToTeamDTO(SpbuTeam entity);

	/**
	 * Создаёт объект студенческой группы на основе DTO.
	 *
	 * @param dto DTO студенческой группы
	 * @return объект студенческой группы
	 */
	@Mappings({
			@Mapping(target = "id", source = "dto.id"),
			@Mapping(target = "name", source = "dto.name"),
			@Mapping(target = "alias", source = "dto.alias")
	})
	SpbuTeam spbuTeamDTOToSpbuTeam(SpbuTeamDTO dto);

	/**
	 * Создаёт список DTO студенческих групп на основе списка объектов студенчких групп.
	 *
	 * @param entity список объектов студенческих групп
	 * @return список DTO
	 */
	List<SpbuTeamDTO> listSpbuTeamToSpbuTeamDTO(List<SpbuTeam> entity);

	/**
	 * Создаёт список студенческих групп на основе списка DTO студенчких групп.
	 *
	 * @param dto список DTO студенческих групп
	 * @return список объектов гупп СПБГУ
	 */
	List<SpbuTeam> listSpbuTeamDTOToSpbuTeam(List<SpbuTeamDTO> dto);
}

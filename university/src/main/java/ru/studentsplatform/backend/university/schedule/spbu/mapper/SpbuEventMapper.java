package ru.studentsplatform.backend.university.schedule.spbu.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuEventDTO;
import ru.studentsplatform.backend.entities.model.spbu.SpbuEvent;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpbuEventMapper {

	/**
	 * Создаёт DTO на основе сущности SpbuEvent.
	 *
	 * @param entity объект студенческого занятия СПБГУ
	 * @return DTO студенческого занятия СПБГУ
	 */
	@Mappings({
			@Mapping(target = "id", source = "entity.id"),
			@Mapping(target = "subject", source = "entity.subject"),
			@Mapping(target = "dayWithTimeInterval", source = "entity.dayWithTimeInterval"),
			@Mapping(target = "timeInterval", source = "entity.timeInterval"),
			@Mapping(target = "location", source = "entity.location"),
			@Mapping(target = "educator", source = "entity.educator"),
			@Mapping(target = "startTime", source = "entity.startTime"),
			@Mapping(target = "endTime", source = "entity.endTime"),
			@Mapping(target = "spbuTeamName", source = "entity.team.name")
	})
	SpbuEventDTO spbuEventToSpbuEventDTO(SpbuEvent entity);

	/**
	 * Создаёт объект студенческого занятия на основе DTO.
	 *
	 * @param dto DTO студенческого занятия
	 * @return объект студенческого занятия
	 */
	@Mappings({
			@Mapping(target = "id", source = "dto.id"),
			@Mapping(target = "subject", source = "dto.subject"),
			@Mapping(target = "dayWithTimeInterval", source = "dto.dayWithTimeInterval"),
			@Mapping(target = "timeInterval", source = "dto.timeInterval"),
			@Mapping(target = "location", source = "dto.location"),
			@Mapping(target = "educator", source = "dto.educator"),
			@Mapping(target = "startTime", source = "dto.startTime"),
			@Mapping(target = "endTime", source = "dto.endTime"),
			@Mapping(target = "team.name", source = "dto.spbuTeamName")
	})
	SpbuEvent spbuEventDTOToSpbuEvent(SpbuEventDTO dto);

	/**
	 * Создаёт список DTO студенческих занятий на основе списка объектов студенчких занятий.
	 *
	 * @param entity список объектов студенческих занятий
	 * @return список DTO
	 */
	List<SpbuEventDTO> listSpbuEventToSpbuEventDTO(List<SpbuEvent> entity);

	/**
	 * Создаёт список студенческих занятий на основе списка DTO студенчких занятий.
	 *
	 * @param dto список DTO студенческих занятий
	 * @return список объектов гупп СПБГУ
	 */
	List<SpbuEvent> listSpbuEventDTOToSpbuEvent(List<SpbuEventDTO> dto);

}

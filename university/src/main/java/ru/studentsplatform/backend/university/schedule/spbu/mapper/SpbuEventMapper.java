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
			@Mapping(target = "teamName", source = "entity.team.name"),
			@Mapping(target = "startTime", source = "entity.startTime"),
			@Mapping(target = "endTime", source = "entity.endTime"),
			@Mapping(target = "date", source = "entity.date"),
			@Mapping(target = "subject", source = "entity.subject"),
			@Mapping(target = "location", source = "entity.location"),
			@Mapping(target = "educator", source = "entity.educator")
	})
	SpbuEventDTO spbuEventToSpbuEventDTO(SpbuEvent entity);

	/**
	 * Создаёт объект студенческого занятия на основе DTO.
	 *
	 * @param dto DTO студенческого занятия
	 * @return объект студенческого занятия
	 */
	@Mappings({
			@Mapping(target = "team.name", source = "dto.teamName"),
			@Mapping(target = "startTime", source = "dto.startTime"),
			@Mapping(target = "endTime", source = "dto.endTime"),
			@Mapping(target = "date", source = "dto.date"),
			@Mapping(target = "subject", source = "dto.subject"),
			@Mapping(target = "location", source = "dto.location"),
			@Mapping(target = "educator", source = "dto.educator")
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

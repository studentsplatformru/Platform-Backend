package ru.studentsplatform.backend.endpoint.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.schedule.ScheduleCellDTO;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleCell;

import java.util.List;

/**
 * Маппер для конвертации объекта сущности ячейки расписания в DTO,
 * а также List'ов, содержащих сущности в List DTO и обратно.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 31.07.2020
 */
@Mapper(componentModel = "spring")
public interface ScheduleCellMapper {
	/**
	 * Трансформация полей сущности ячейки расписания в поля DTO.
	 *
	 * @param entity Сущность ячейки расписания, на основе которой будет создан DTO
	 * @return DTO ячейки расписания.
	 */
	@Mappings({
			@Mapping(target = "id", source = "entity.id"),
			@Mapping(target = "createdBy", source = "entity.createdBy"),
			@Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
			@Mapping(target = "startClass", source = "entity.startClass"),
			@Mapping(target = "endClass", source = "entity.endClass"),
			@Mapping(target = "type", source = "entity.type"),
			@Mapping(target = "semester", source = "entity.semester"),
			@Mapping(target = "teamId", source = "entity.team.id"),
			@Mapping(target = "subjectId", source = "entity.subject.id")

	})
	ScheduleCellDTO scheduleCellToScheduleCellDTO(ScheduleCell entity);

	/**
	 * Трансформация полей объекта DTO ячейки расписания в поля сущности ячейки расписания.
	 *
	 * @param dto DTO ячейки расписания, на основе которого будет создана сущность.
	 * @return Объект сущности ячейки расписания.
	 */
	@Mappings({
			@Mapping(target = "id", source = "dto.id"),
			@Mapping(target = "createdBy", source = "dto.createdBy"),
			@Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
			@Mapping(target = "startClass", source = "dto.startClass"),
			@Mapping(target = "endClass", source = "dto.endClass"),
			@Mapping(target = "type", source = "dto.type"),
			@Mapping(target = "semester", source = "dto.semester"),
			@Mapping(target = "team.id", source = "dto.teamId"),
			@Mapping(target = "subject.id", source = "dto.subjectId")

	})
	ScheduleCell scheduleCellDTOToScheduleCell(ScheduleCellDTO dto);

	/**
	 * Конвертирует сразу несколько Сущностей в DTO.
	 *
	 * @param entity Список ячеек расписания.
	 * @return Список DTO.
	 */
	List<ScheduleCellDTO> listScheduleCellToScheduleCellDTO(List<ScheduleCell> entity);

	/**
	 * Конвертирует сразу несколько DTO в сущности ячеек расписания.
	 *
	 * @param dto Список DTO.
	 * @return Список ячеек расписания
	 */
	List<ScheduleCell> listScheduleCellDTOToScheduleCell(List<ScheduleCellDTO> dto);

}

package ru.studentsplatform.backend.endpoint.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.university.PlaceStudyDTO;
import ru.studentsplatform.backend.entities.model.university.PlaceStudy;

import java.util.List;

/**
 * Маппер для конвертации объекта сущности места учёбы в DTO,
 * а также List'ов, содержащих сущности в List DTO и обратно.
 */
@Mapper(componentModel = "spring")

public interface PlaceStudyMapper {
	/**
	 * Трансформация полей сущности места учёбы в поля DTO.
	 *
	 * @param entity Сущность места учёбы, на основе которой будет создан DTO
	 * @return DTO места учёбы.
	 */
	@Mappings({
			@Mapping(target = "id", source = "entity.id"),
			@Mapping(target = "createdBy", source = "entity.createdBy"),
			@Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
			@Mapping(target = "userId", source = "entity.user.id"),
			@Mapping(target = "universityId", source = "entity.university.id"),
			@Mapping(target = "facultyId", source = "entity.faculty.id"),
			@Mapping(target = "departmentId", source = "entity.department.id"),
			@Mapping(target = "directionId", source = "entity.direction.id"),
			@Mapping(target = "teamId", source = "entity.team.id"),
			@Mapping(target = "semester", source = "entity.semester")
	})
	PlaceStudyDTO placeStudyToPlaceStudyDTO(PlaceStudy entity);

	/**
	 * Трансформация полей объекта DTO места учёбы в поля сущности места учёбы.
	 *
	 * @param dto DTO места учёбы, на основе которого будет создана сущность.
	 * @return Объект сущности места учёбы.
	 */
	@Mappings({
			@Mapping(target = "id", source = "dto.id"),
			@Mapping(target = "createdBy", source = "dto.createdBy"),
			@Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
			@Mapping(target = "user.id", source = "dto.userId"),
			@Mapping(target = "university.id", source = "dto.universityId"),
			@Mapping(target = "faculty.id", source = "dto.facultyId"),
			@Mapping(target = "department.id", source = "dto.departmentId"),
			@Mapping(target = "direction.id", source = "dto.directionId"),
			@Mapping(target = "team.id", source = "dto.teamId"),
			@Mapping(target = "semester", source = "dto.semester")
	})
	PlaceStudy placeStudyDTOToPlaceStudy(PlaceStudyDTO dto);

	/**
	 * Конвертирует сразу несколько Сущностей в DTO.
	 *
	 * @param entity Список мест занятий.
	 * @return Список DTO.
	 */
	List<PlaceStudyDTO> listPlaceStudyToPlaceStudyDTO(List<PlaceStudy> entity);

	/**
	 * Конвертирует сразу несколько DTO в сущности мест занятий.
	 *
	 * @param dto Список DTO.
	 * @return Список мест занятий
	 */
	List<PlaceStudy> listPlaceStudyDTOToPlaceStudy(List<PlaceStudyDTO> dto);
}


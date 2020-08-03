package ru.studentsplatform.backend.endpoint.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.PlaceStudyDTO;
import ru.studentsplatform.backend.entities.model.university.PlaceStudy;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.List;

/**
 * Маппер для конвертации объекта сущности места учёбы в DTO,
 * а также List'ов, содержащих сущности в List DTO и обратно.
 *
 * @author Perevalov Pavel 28.07.2020
 */
@Profiled
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
			@Mapping(target = "user", source = "entity.user"),
			@Mapping(target = "university", source = "entity.university"),
			@Mapping(target = "faculty", source = "entity.faculty"),
			@Mapping(target = "department", source = "entity.department"),
			@Mapping(target = "direction", source = "entity.direction"),
			@Mapping(target = "team", source = "entity.team"),
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
			@Mapping(target = "user", source = "dto.user"),
			@Mapping(target = "university", source = "dto.university"),
			@Mapping(target = "faculty", source = "dto.faculty"),
			@Mapping(target = "department", source = "dto.department"),
			@Mapping(target = "direction", source = "dto.direction"),
			@Mapping(target = "team", source = "dto.team"),
			@Mapping(target = "semester", source = "dto.semester")
	})
	PlaceStudy placeStudyDTOToPlaceStudy(PlaceStudyDTO dto);

	/**
	 * Конвертирует сразу несколько Сущностей в DTO.
	 *
	 * @param entity Список места учёбы.
	 * @return Список DTO.
	 */
	List<PlaceStudyDTO> listPlaceStudyToPlaceStudyDTO(List<PlaceStudy> entity);

	/**
	 * Конвертирует сразу несколько DTO в сущности места учёбы.
	 *
	 * @param dto Список DTO.
	 * @return Список мест учёбы
	 */
	List<PlaceStudy> listPlaceStudyDTOToPlaceStudy(List<PlaceStudyDTO> dto);
}
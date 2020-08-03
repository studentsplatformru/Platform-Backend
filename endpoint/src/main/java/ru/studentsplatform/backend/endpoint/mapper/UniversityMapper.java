package ru.studentsplatform.backend.endpoint.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.university.UniversityDTO;
import ru.studentsplatform.backend.entities.model.university.University;

import java.util.List;

/**
 * Маппер для конвертации объекта сущности университета в DTO,
 * а также List'ов, содержащих сущности в List DTO и обратно.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 31.07.2020
 */
@Mapper(componentModel = "spring", uses = {FacultyMapper.class})
public interface UniversityMapper {
	/**
	 * Трансформация полей сущности университета в поля DTO.
	 *
	 * @param entity Сущность университета, на основе которой будет создан DTO
	 * @return DTO университета.
	 */
	@Mappings({
			@Mapping(target = "id", source = "entity.id"),
			@Mapping(target = "createdBy", source = "entity.createdBy"),
			@Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
			@Mapping(target = "university", source = "entity.university"),
			@Mapping(target = "faculties", source = "entity.faculties")

	})
	UniversityDTO universityToUniversityDTO(University entity);

	/**
	 * Трансформация полей объекта DTO университета в поля сущности университета.
	 *
	 * @param dto DTO университета, на основе которого будет создана сущность.
	 * @return Объект сущности университета.
	 */
	@Mappings({
			@Mapping(target = "id", source = "dto.id"),
			@Mapping(target = "createdBy", source = "dto.createdBy"),
			@Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
			@Mapping(target = "university", source = "dto.university"),
			@Mapping(target = "faculties", source = "dto.faculties")

	})
	University universityDTOToUniversity(UniversityDTO dto);

	/**
	 * Конвертирует сразу несколько Сущностей в DTO.
	 *
	 * @param entity Список университетов.
	 * @return Список DTO.
	 */
	List<UniversityDTO> listUniversityToUniversityDTO(List<University> entity);

	/**
	 * Конвертирует сразу несколько DTO в сущности университетов.
	 *
	 * @param dto Список DTO.
	 * @return Список университетов
	 */
	List<University> listUniversityDTOToUniversity(List<UniversityDTO> dto);

}

package ru.studentsplatform.backend.endpoint.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.university.FacultyDTO;
import ru.studentsplatform.backend.entities.model.university.Faculty;

import java.util.List;

/**
 * Маппер для конвертации объекта сущности института в DTO,
 * а также List'ов, содержащих сущности в List DTO и обратно.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 31.07.2020
 */
@Mapper(componentModel = "spring", uses = {DepartmentMapper.class, DirectionMapper.class})
public interface FacultyMapper {
	/**
	 * Трансформация полей сущности института в поля DTO.
	 *
	 * @param entity Сущность института, на основе которой будет создан DTO
	 * @return DTO института.
	 */
	@Mappings({
			@Mapping(target = "id", source = "entity.id"),
			@Mapping(target = "createdBy", source = "entity.createdBy"),
			@Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
			@Mapping(target = "faculty", source = "entity.faculty"),
			@Mapping(target = "universityId", source = "entity.university.id"),
			@Mapping(target = "departments", source = "entity.departments"),
			@Mapping(target = "directions", source = "entity.directions")
	})
	FacultyDTO facultyToFacultyDTO(Faculty entity);

	/**
	 * Трансформация полей объекта DTO института в поля сущности института.
	 *
	 * @param dto DTO института, на основе которого будет создана сущность.
	 * @return Объект сущности института.
	 */
	@Mappings({
			@Mapping(target = "id", source = "dto.id"),
			@Mapping(target = "createdBy", source = "dto.createdBy"),
			@Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
			@Mapping(target = "faculty", source = "dto.faculty"),
			@Mapping(target = "university.id", source = "dto.universityId"),
			@Mapping(target = "departments", source = "dto.departments"),
			@Mapping(target = "directions", source = "dto.directions")

	})
	Faculty facultyDTOToFaculty(FacultyDTO dto);

	/**
	 * Конвертирует сразу несколько Сущностей в DTO.
	 *
	 * @param entity Список институтов.
	 * @return Список DTO.
	 */
	List<FacultyDTO> listFacultyToFacultyDTO(List<Faculty> entity);

	/**
	 * Конвертирует сразу несколько DTO в сущности институтов.
	 *
	 * @param dto Список DTO.
	 * @return Список институтов
	 */
	List<Faculty> listFacultyDTOToFaculty(List<FacultyDTO> dto);

}

package ru.studentsplatform.backend.endpoint.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.university.DisciplineDTO;
import ru.studentsplatform.backend.entities.model.university.Discipline;

import java.util.List;

/**
 * Маппер для конвертации объекта сущности дисциплины в DTO,
 * а также List'ов, содержащих сущности в List DTO и обратно.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 31.07.2020
 */
@Mapper(componentModel = "spring", uses = {ScheduleUserCellMapper.class})
public interface DisciplineMapper {
	/**
	 * Трансформация полей сущности дисциплины в поля DTO.
	 *
	 * @param entity Сущность дисциплины, на основе которой будет создан DTO
	 * @return DTO дисциплины.
	 */
	@Mappings({
			@Mapping(target = "id", source = "entity.id"),
			@Mapping(target = "createdBy", source = "entity.createdBy"),
			@Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
			@Mapping(target = "userId", source = "entity.user.id"),
			@Mapping(target = "subjectId", source = "entity.subject.id"),
			@Mapping(target = "semester", source = "entity.semester"),
			@Mapping(target = "scheduleUserCells", source = "entity.scheduleUserCells")
	})
	DisciplineDTO disciplineToDisciplineDTO(Discipline entity);

	/**
	 * Трансформация полей объекта DTO дисциплины в поля сущности дисциплины.
	 *
	 * @param dto DTO дисциплины, на основе которого будет создана сущность.
	 * @return Объект сущности дисциплины.
	 */
	@Mappings({
			@Mapping(target = "id", source = "dto.id"),
			@Mapping(target = "createdBy", source = "dto.createdBy"),
			@Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
			@Mapping(target = "user.id", source = "dto.userId"),
			@Mapping(target = "subject.id", source = "dto.subjectId"),
			@Mapping(target = "semester", source = "dto.semester"),
			@Mapping(target = "scheduleUserCells", source = "dto.scheduleUserCells")
	})
	Discipline disciplineDTOToDiscipline(DisciplineDTO dto);

	/**
	 * Конвертирует сразу несколько Сущностей в DTO.
	 *
	 * @param entity Список дисциплин.
	 * @return Список DTO.
	 */
	List<DisciplineDTO> listDisciplineToDisciplineDTO(List<Discipline> entity);

	/**
	 * Конвертирует сразу несколько DTO в сущности дисциплин.
	 *
	 * @param dto Список DTO.
	 * @return Список дисциплин
	 */
	List<Discipline> listDisciplineDTOToDiscipline(List<DisciplineDTO> dto);

}

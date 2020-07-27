package ru.studentsplatform.backend.endpoint.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.TaskAttachmentDTO;
import ru.studentsplatform.backend.entities.model.utility.TaskAttachment;

import java.util.List;

@Mapper(componentModel = "spring")

public interface TaskAttachmentMapper {

	/**
	 * Трансформация полей сущности приложений к задаче в поля DTO.
	 *
	 * @param entity Сущность приложения к задаче, на основе которой будет создан DTO
	 * @return DTO приложения к задаче.
	 */
	@Mappings({
			@Mapping(target = "id", source = "entity.id"),
			@Mapping(target = "createdBy", source = "entity.createdBy"),
			@Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
			@Mapping(target = "fileName", source = "entity.fileName"),
			@Mapping(target = "content", source = "entity.content"),
			@Mapping(target = "taskId", source = "entity.task.id")
	})
	TaskAttachmentDTO taskAttachmentToTaskAttachmentDTO(TaskAttachment entity);

	/**
	 * Трансформация полей объекта DTO приложения к задаче в поля сущности приложения к задаче.
	 *
	 * @param dto DTO приложения к задаче, на основе которого будет создана сущность.
	 * @return Объект сущности приложения к задаче.
	 */
	@Mappings({
			@Mapping(target = "id", source = "dto.id"),
			@Mapping(target = "createdBy", source = "dto.createdBy"),
			@Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
			@Mapping(target = "fileName", source = "dto.fileName"),
			@Mapping(target = "content", source = "dto.content"),
			@Mapping(target = "task.id", source = "dto.taskId")
	})
	TaskAttachment taskAttachmentDTOToTaskAttachment(TaskAttachmentDTO dto);

	/**
	 * Конвертирует сразу несколько Сущностей в DTO.
	 *
	 * @param entity Список приложений к задаче.
	 * @return Список DTO.
	 */
	List<TaskAttachmentDTO> listTaskAttachmentToTaskAttachmentDTO(List<TaskAttachment> entity);

	/**
	 * Конвертирует сразу несколько DTO в сущности приложений к задаче.
	 *
	 * @param dto Список DTO.
	 * @return Список приложений к задаче
	 */
	List<TaskAttachment> listTaskAttachmentDTOToTaskAttachment(List<TaskAttachmentDTO> dto);

}

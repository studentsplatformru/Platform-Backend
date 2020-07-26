package ru.studentsplatform.backend.endpoint.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.TaskDTO;
import ru.studentsplatform.backend.entities.model.university.Task;

import java.util.List;

/**
 * Маппер для конвертации объекта сущности задачи для студента в DTO,
 * а также List'ов, содержащих сущности в List DTO и обратно.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 26.07.2020
 */
@Mapper(componentModel = "spring")
public interface TaskMapper {
	/**
	 * Трансформация полей сущности задачи в поля DTO.
	 *
	 * @param entity Сущность задачи, на основе которой будет создан DTO
	 * @return DTO задачи.
	 */
	@Mappings({
			@Mapping(target = "id", source = "entity.id"),
			@Mapping(target = "createdBy", source = "entity.createdBy"),
			@Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
			@Mapping(target = "taskName", source = "entity.taskName"),
			@Mapping(target = "isDone", source = "entity.isDone"),
			@Mapping(target = "mark", source = "entity.mark"),
			@Mapping(target = "scheduleUserCellId", source = "entity.scheduleUserCell.id"),
			@Mapping(target = "deadLine", source = "entity.deadLine")

	})
	TaskDTO taskToTaskDTO(Task entity);

	/**
	 * Трансформация полей объекта DTO задачи в поля сущности задачи.
	 *
	 * @param dto DTO задачи, на основе которого будет создана сущность.
	 * @return Объект сущности задачи.
	 */
	@Mappings({
			@Mapping(target = "id", source = "dto.id"),
			@Mapping(target = "createdBy", source = "dto.createdBy"),
			@Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
			@Mapping(target = "taskName", source = "dto.taskName"),
			@Mapping(target = "isDone", source = "dto.isDone"),
			@Mapping(target = "mark", source = "dto.mark"),
			@Mapping(target = "scheduleUserCell.id", source = "dto.scheduleUserCellId"),
			@Mapping(target = "deadLine", source = "dto.deadLine")

	})
	Task taskDTOToTask(TaskDTO dto);

	/**
	 * Конвертирует сразу несколько Сущностей в DTO.
	 *
	 * @param entity Список задач.
	 * @return Список DTO.
	 */
	List<TaskDTO> listTaskToTaskDTO(List<Task> entity);

	/**
	 * Конвертирует сразу несколько DTO в сущности задач.
	 *
	 * @param dto Список DTO.
	 * @return Список задач
	 */
	List<Task> listTaskDTOToTask(List<TaskDTO> dto);

}

package ru.studentsplatform.backend.domain.dto.university;

import lombok.Data;
import ru.studentsplatform.backend.domain.dto.utility.TaskAttachmentDTO;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Класс DTO, хранящий сведения о сущности студенчекой задачи.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 26.07.2020
 */
@Data
public class TaskDTO extends ru.studentsplatform.backend.domain.dto.BaseDTO {

	private String taskName;

	private OffsetDateTime deadLine;

	private Boolean isDone;

	private Integer mark;

	private Long scheduleUserCellId;

	private List<TaskAttachmentDTO> attachments;
}

package ru.studentsplatform.backend.domain.dto;

import lombok.Data;

@Data
public class TaskAttachmentDTO extends BaseDTO {

	private String fileName;

	private byte[] content;

	private Long taskId;

	private String contentType;
}

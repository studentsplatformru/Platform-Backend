package ru.studentsplatform.backend.domain.dto.utility;

import lombok.Data;
import ru.studentsplatform.backend.domain.dto.BaseDTO;

@Data
public class TaskAttachmentDTO extends BaseDTO {

	private String fileName;

	private byte[] content;

	private Long taskId;

	private String contentType;
}

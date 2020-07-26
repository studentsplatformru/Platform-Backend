package ru.studentsplatform.backend.service.crud;

import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.entities.model.utility.TaskAttachment;

/**
 * //TODO.
 *
 * @author Krylov Sergey (26.07.2020)
 */
public interface TaskAttachmentService extends AbstractService<TaskAttachment> {
	TaskAttachment createByFile(Task task, MultipartFile file);
}

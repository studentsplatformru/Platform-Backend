package ru.studentsplatform.backend.service.crud;

import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.entities.model.utility.TaskAttachment;

import java.util.List;

/**
 * //TODO.
 *
 * @author Krylov Sergey (26.07.2020)
 */
public interface TaskAttachmentService extends AbstractService<TaskAttachment> {

	@Override
	TaskAttachment create(TaskAttachment newEntity);

	@Override
	TaskAttachment getById(Long id);

	@Override
	List<TaskAttachment> getAll();

	@Override
	TaskAttachment update(TaskAttachment updatedEntity, Long id);

	@Override
	boolean delete(Long id);

	TaskAttachment getByTaskId(Long taskId);

	TaskAttachment createByFile(Task task, MultipartFile file);
}

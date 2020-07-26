package ru.studentsplatform.backend.service.crud.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.repository.TaskAttachmentRepository;
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.entities.model.utility.TaskAttachment;
import ru.studentsplatform.backend.service.crud.TaskAttachmentService;
import ru.studentsplatform.backend.system.annotation.Profiled;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

/**
 * @author Krylov Sergey (26.07.2020)
 */
@Profiled
@Service
@Transactional
public class TaskAttachmentServiceImpl implements TaskAttachmentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskAttachmentServiceImpl.class);

	private final TaskAttachmentRepository taskAttachmentRepository;

	public TaskAttachmentServiceImpl(TaskAttachmentRepository taskAttachmentRepository) {
		this.taskAttachmentRepository = taskAttachmentRepository;
	}

	@Override
	public TaskAttachment create(TaskAttachment taskAttachment) {

		return null;
	}

	@Override
	@Transactional
	public TaskAttachment getById(Long taskId) {
		var taskAttachment = taskAttachmentRepository.findByTaskId(taskId);
		return taskAttachment;
	}

	@Override
	public List<TaskAttachment> getAll() {
		return null;
	}

	@Override
	public TaskAttachment update(TaskAttachment updatedEntity, Long id) {
		return null;
	}

	@Override
	public boolean delete(Long id) {
		return false;
	}

	@Override
	public TaskAttachment createByFile(Task task, MultipartFile file) {
		var taskAttachment = new TaskAttachment();

		try {
			taskAttachment.setFileName(file.getName());
			taskAttachment.setContent(file.getBytes());
			taskAttachment.setTask(task);
			taskAttachment = taskAttachmentRepository.save(taskAttachment);
		} catch (IOException e) {
			LOGGER.error("Error occurred while adding task attachments!");
		}

		return taskAttachment;
	}
}

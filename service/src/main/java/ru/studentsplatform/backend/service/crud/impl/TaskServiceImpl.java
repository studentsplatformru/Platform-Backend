package ru.studentsplatform.backend.service.crud.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.repository.TaskRepository;
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.service.crud.TaskAttachmentService;
import ru.studentsplatform.backend.service.crud.TaskService;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.service.exception.core.BusinessException;
import ru.studentsplatform.backend.system.annotation.Profiled;

import java.util.List;
import java.util.NoSuchElementException;

@Profiled
@Service
public class TaskServiceImpl implements TaskService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

	private final TaskRepository taskRepository;

	private final TaskAttachmentService taskAttachmentService;

	public TaskServiceImpl(TaskRepository taskRepository,
						   TaskAttachmentService taskAttachmentService) {
		this.taskRepository = taskRepository;
		this.taskAttachmentService = taskAttachmentService;
	}

	@Override
	public Task create(Task task) {
		if (task.getScheduleUserCell().getId() == null) {
			throw new BusinessException(ServiceExceptionReason.SCHEDULE_CELL_NOT_FOUND);
		}
		return taskRepository.save(task);
	}

	@Override
	public Task getById(Long id) {
		return taskRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public List<Task> getAll() {
		return taskRepository.findAll();
	}

	@Override
	public Task update(Task updatedEntity, Long id) {
		updatedEntity.setId(id);
		return taskRepository.saveAndFlush(updatedEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(Long id) {
		try {
			taskRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addFileForTask(Long taskId, MultipartFile file) {
		if (file == null) {
			// TODO бизнес ошибка
			throw new RuntimeException("You must select the a file for uploading");
		}
		var task = taskRepository.getOne(taskId);
		var taskAttachment = taskAttachmentService.createByFile(task, file);
		task.getAttachments().add(taskAttachment);

		return true;
	}
}

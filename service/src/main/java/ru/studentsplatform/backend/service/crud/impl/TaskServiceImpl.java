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
import ru.studentsplatform.backend.system.helper.CollectionUtils;

import java.util.List;
import java.util.NoSuchElementException;

@Profiled
@Service
public class TaskServiceImpl implements TaskService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

	private final TaskRepository taskRepository;

	private final TaskAttachmentService taskAttachmentService;

	/**
	 * Консруктор.
	 *
	 * @param taskRepository		Репозиторий
	 * @param taskAttachmentService Сервис прикрепляемых к task файлов
	 */
	public TaskServiceImpl(TaskRepository taskRepository,
						   TaskAttachmentService taskAttachmentService) {
		this.taskRepository = taskRepository;
		this.taskAttachmentService = taskAttachmentService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Task create(Task task) {
		if (task.getScheduleUserCell() == null || task.getScheduleUserCell().getId() == null) {
			throw new BusinessException(ServiceExceptionReason.SCHEDULE_CELL_NOT_FOUND, task.getTaskName());
		}
		return taskRepository.save(task);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Task getById(Long id) {
		return taskRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Task> getAll() {
		return taskRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
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
			LOGGER.error("Error occured: cannot delete non-existent task");
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addFilesForTask(Long taskId, List<MultipartFile> files) {
		if (CollectionUtils.notEmpty(files)) {
			for (MultipartFile file: files) {
				if (file == null) {
					throw new BusinessException(ServiceExceptionReason.NULL_FILE_EXCEPTION, taskId);
				}
				var task = taskRepository.getOne(taskId);
				taskAttachmentService.createByFile(task, file);
			}
			return true;
		}

		throw new BusinessException(ServiceExceptionReason.NO_UPLOADED_FILES_FOUND, taskId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Task> getByUserCell(Long userCellId) {
		return taskRepository.findByScheduleUserCellId(userCellId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Task> getByUser(Long userId) {
		return taskRepository.findByUserId(userId);
	}
}

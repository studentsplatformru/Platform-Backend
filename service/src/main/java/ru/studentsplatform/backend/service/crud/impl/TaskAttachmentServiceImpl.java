package ru.studentsplatform.backend.service.crud.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.repository.TaskAttachmentRepository;
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.entities.model.utility.TaskAttachment;
import ru.studentsplatform.backend.service.crud.TaskAttachmentService;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.system.exception.core.BusinessException;
import ru.studentsplatform.backend.system.helper.CollectionUtils;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Krylov Sergey (26.07.2020)
 */
@Profiled
@Service
public class TaskAttachmentServiceImpl implements TaskAttachmentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskAttachmentServiceImpl.class);

	private final TaskAttachmentRepository taskAttachmentRepository;

	/**
	 * Конструктор.
	 *
	 * @param taskAttachmentRepository Репозиторий приложения к задаче
	 */
	public TaskAttachmentServiceImpl(TaskAttachmentRepository taskAttachmentRepository) {
		this.taskAttachmentRepository = taskAttachmentRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TaskAttachment create(TaskAttachment taskAttachment) {
		return taskAttachmentRepository.saveAndFlush(taskAttachment);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TaskAttachment getById(Long id) {
		return taskAttachmentRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TaskAttachment> getAll() {
		return taskAttachmentRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TaskAttachment update(TaskAttachment updatedEntity, Long id) {
		updatedEntity.setId(id);
		return taskAttachmentRepository.saveAndFlush(updatedEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(Long id) {
		try {
			taskAttachmentRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Error occured: cannot delete non-existent task attachment");
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TaskAttachment createByFile(Task task, MultipartFile file) {
		var taskAttachment = new TaskAttachment();

		try {
			taskAttachment.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
			taskAttachment.setContent(file.getBytes());
			taskAttachment.setTask(task);
			taskAttachment.setContentType(file.getContentType());
			taskAttachment = taskAttachmentRepository.save(taskAttachment);
		} catch (IOException e) {
			LOGGER.error("Error occurred while adding task attachments!");
		}

		return taskAttachment;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TaskAttachment> getByTaskId(Long taskId) {
		return taskAttachmentRepository.findByTaskId(taskId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TaskAttachment getByFileId(Long taskId, Long attachmentID) {
		List<TaskAttachment> attachments = getByTaskId(taskId);
		if (CollectionUtils.notEmpty(attachments)) {
			return attachments.stream()
					.filter(element -> element.getId().equals(attachmentID)).findFirst()
					.orElseThrow(() ->
							new BusinessException(ServiceExceptionReason.FILE_INDEX_NOT_EXIST, attachmentID, taskId));
		}
		throw new BusinessException(ServiceExceptionReason.FILE_INDEX_NOT_EXIST, attachmentID, taskId);
	}

}

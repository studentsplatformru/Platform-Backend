package ru.studentsplatform.backend.service.crud.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.repository.TaskAttachmentRepository;
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.entities.model.utility.TaskAttachment;
import ru.studentsplatform.backend.service.crud.TaskAttachmentService;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.service.exception.core.BusinessException;
import ru.studentsplatform.backend.system.annotation.Profiled;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Krylov Sergey (26.07.2020)
 */
@Profiled
@Service
@Transactional
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

	@Override
	public TaskAttachment create(TaskAttachment taskAttachment) {
		return taskAttachmentRepository.saveAndFlush(taskAttachment);
	}

	@Override
	public TaskAttachment getById(Long id) {
		return taskAttachmentRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public List<TaskAttachment> getAll() {
		return taskAttachmentRepository.findAll();
	}

	@Override
	public TaskAttachment update(TaskAttachment updatedEntity, Long id) {
		updatedEntity.setId(id);
		return taskAttachmentRepository.saveAndFlush(updatedEntity);
	}

	@Override
	public boolean delete(Long id) {
		try {
			taskAttachmentRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
		return true;
	}

	/**
	 * Прикрепление файла к task и сохранение его в БД.
	 *
	 * @param task	Задание, к которому прикрепляются файлы
	 * @param file	Файл, который будет прикрепляться
	 * @return 		Прикреплённый файл
	 */
	@Override
	@Transactional
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

	@Override
	@Transactional
	public List<TaskAttachment> getByTaskId(Long taskId) {
		return taskAttachmentRepository.findByTaskId(taskId);
	}

	@Override
	public TaskAttachment getByFileIndex(Long taskId, int index) {
		List<TaskAttachment> attachments = getByTaskId(taskId);
		if (attachments.size() > 0 && index > 0 && index <= attachments.size()) {
			return attachments.get(index - 1);
		}
		throw new BusinessException(ServiceExceptionReason.FILE_INDEX_NOT_EXIST);
	}

}

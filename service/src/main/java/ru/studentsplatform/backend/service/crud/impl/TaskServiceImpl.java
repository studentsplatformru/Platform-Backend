package ru.studentsplatform.backend.service.crud.impl;

import com.querydsl.core.BooleanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.dto.TaskFilterDTO;
import ru.studentsplatform.backend.domain.repository.TaskRepository;
import ru.studentsplatform.backend.entities.model.university.QTask;
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.service.crud.TaskAttachmentService;
import ru.studentsplatform.backend.service.crud.TaskService;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.system.exception.core.BusinessException;
import ru.studentsplatform.backend.system.helper.CollectionUtils;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Profiled
@Service
public class TaskServiceImpl implements TaskService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

	private final TaskRepository taskRepository;

	private final TaskAttachmentService taskAttachmentService;

	/**
	 * Консруктор.
	 *
	 * @param taskRepository        Репозиторий
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
			throw new BusinessException(ServiceExceptionReason.SCHEDULE_CELL_NOT_FOUND, task.getId());
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
			for (MultipartFile file : files) {
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
	public List<Task> getByFilters(TaskFilterDTO taskFilterDTO) {
		// Получение статического инстанса QTask'a - класса для генерации строк, связанных с Task (сущностью задач).
		QTask qTask = QTask.task;
		// Билдер предиката, который потом вставляется в FindAll().
		BooleanBuilder where = new BooleanBuilder();
		// Проверяем, задан ли вообще фильтр.
		if (taskFilterDTO.getStartTime() != null) {
			// Если да, то добавляем в предикат соответствующее выражение (в данном случае больше
			// чем заданное начальное время). Выражение само по себе предикат. Как раз для получения
			// выражения нужны Q class'ы. А потом эти выражения комбинируем с помощью билдера.
			where.and(qTask.scheduleUserCell.scheduleCell.startClass.goe(taskFilterDTO.getStartTime()));
		}
		if (taskFilterDTO.getEndTime() != null) {
			where.and(qTask.scheduleUserCell.scheduleCell.endClass.loe(taskFilterDTO.getEndTime()));
		}
		if (taskFilterDTO.getUserId() != null) {
			where.and(qTask.scheduleUserCell.user.id.eq(taskFilterDTO.getUserId()));
		}
		if (taskFilterDTO.getSubjectId() != null) {
			where.and(qTask.scheduleUserCell.scheduleCell.subject.id.eq(taskFilterDTO.getSubjectId()));
		}
		if (taskFilterDTO.getGroupId() != null) {
			where.and(qTask.scheduleUserCell.scheduleCell.team.id.eq(taskFilterDTO.getGroupId()));
		}
		if (taskFilterDTO.getSemester() != null) {
			where.and(qTask.scheduleUserCell.discipline.semester.eq(taskFilterDTO.getSemester()));
		}
		if (taskFilterDTO.getUserCellId() != null) {
			where.and(qTask.scheduleUserCell.id.eq(taskFilterDTO.getUserCellId()));
		}

		return StreamSupport.stream(taskRepository.findAll(where).spliterator(),
				false).collect(Collectors.toList());
	}
}

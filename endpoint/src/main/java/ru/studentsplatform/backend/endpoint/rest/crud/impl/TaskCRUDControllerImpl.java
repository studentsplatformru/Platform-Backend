package ru.studentsplatform.backend.endpoint.rest.crud.impl;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.dto.university.TaskDTO;
import ru.studentsplatform.backend.endpoint.mapper.TaskMapper;
import ru.studentsplatform.backend.endpoint.rest.crud.TaskCRUDController;
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.service.crud.TaskAttachmentService;
import ru.studentsplatform.backend.service.crud.impl.TaskServiceImpl;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.Arrays;
import java.util.List;

@Profiled
@RestController
@RequestMapping(TaskCRUDController.BASE_URL)
public class TaskCRUDControllerImpl implements TaskCRUDController {
	private final TaskMapper taskMapper;

	private final TaskServiceImpl taskService;

	private final TaskAttachmentService taskAttachmentService;

	/**
	 * Конструктор.
	 *
	 * @param taskMapper            Task маппер
	 * @param taskService           Сервис с методами для работы с Task
	 * @param taskAttachmentService Сервис с методами для работы с файлами, прикрепленными к Task
	 */
	public TaskCRUDControllerImpl(TaskMapper taskMapper,
								  TaskServiceImpl taskService, TaskAttachmentService taskAttachmentService) {
		this.taskMapper = taskMapper;
		this.taskService = taskService;
		this.taskAttachmentService = taskAttachmentService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<TaskDTO> getTask(Long taskId) {

		Task task = taskService.getById(taskId);
		TaskDTO result = taskMapper.taskToTaskDTO(task);

		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<TaskDTO>> getAllTasksForCell(Long cellId) {

		List<Task> taskList = taskService.getByUserCell(cellId);
		List<TaskDTO> result = taskMapper.listTaskToTaskDTO(taskList);

		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<TaskDTO> createTask(Long cellId,
											  TaskDTO dto) {

		dto.setScheduleUserCellId(cellId);
		var task = taskMapper.taskDTOToTask(dto);
		task = taskService.create(task);
		var result = taskMapper.taskToTaskDTO(task);

		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<Boolean> taskAddFiles(Long taskId,
												MultipartFile... files) {

		var result = taskService.addFilesForTask(taskId, Arrays.asList(files));

		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<Resource> getFileRelatedToTask(Long taskId,
														 Long fileId) {

		var file = taskAttachmentService.getByFileId(taskId, fileId);
		var fileName = file.getFileName();

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").
						contentType(MediaType.parseMediaType(file.getContentType()))
				.body(new ByteArrayResource(file.getContent()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<TaskDTO>> getByDoneTaskForUser(Long userId,
															  Boolean isDone) {
		var entityList = taskService.getByIsDoneByUserId(userId, isDone);
		var result = taskMapper.listTaskToTaskDTO(entityList);
		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<TaskDTO>> getTaskBySemesterForUser(Long userId,
																  Integer semester) {
		var entityList = taskService.getBySemesterForUser(userId, semester);
		var result = taskMapper.listTaskToTaskDTO(entityList);
		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<TaskDTO>> getTaskBySubjectForUser(Long userId,
																 Long subjectId) {
		var entityList = taskService.getBySubjectForUser(userId, subjectId);
		var result = taskMapper.listTaskToTaskDTO(entityList);
		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<TaskDTO>> getTaskByGroup(Long groupID) {
		var entityList = taskService.getByTeamId(groupID);
		var result = taskMapper.listTaskToTaskDTO(entityList);
		return ResponseEntity.ok(result);
	}

	//TODO: redirect to createTask() method
	@Override
	public ResponseEntity<TaskDTO> create(TaskDTO dto) {
		return null;
	}

	//TODO: redirect to getTask() method
	@Override
	public ResponseEntity<TaskDTO> getById(Long id) {
		return null;
	}

	//TODO: redirect to getAllTasks() method
	@Override
	public ResponseEntity<List<TaskDTO>> getAll() {
		return null;
	}

	//TODO
	@Override
	public ResponseEntity<TaskDTO> update(TaskDTO updatedInstanceRequest, Long id) {
		return null;
	}

	//TODO
	@Override
	public ResponseEntity<Boolean> delete(Long id) {
		return null;
	}
}

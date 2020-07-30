package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.dto.TaskDTO;
import ru.studentsplatform.backend.endpoint.mapper.TaskMapper;
import ru.studentsplatform.backend.endpoint.rest.TaskController;
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.service.crud.TaskAttachmentService;
import ru.studentsplatform.backend.service.crud.impl.TaskServiceImpl;
import ru.studentsplatform.backend.system.annotation.Profiled;

import java.util.Arrays;
import java.util.List;

@Profiled
@RestController
@RequestMapping
public class TaskControllerImpl implements TaskController {
	private final TaskMapper taskMapper;

	private final TaskServiceImpl taskService;

	private final TaskAttachmentService taskAttachmentService;

	/**
	 * Конструктор.
	 *
	 * @param taskMapper            Task маппер
	 * @param taskService           Сервис с методами для работы с Task
	 * @param taskAttachmentService Сервис
	 */
	public TaskControllerImpl(TaskMapper taskMapper,
							  TaskServiceImpl taskService, TaskAttachmentService taskAttachmentService) {
		this.taskMapper = taskMapper;
		this.taskService = taskService;
		this.taskAttachmentService = taskAttachmentService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<TaskDTO> gatTask(@PathVariable(name = "userId") Long userId,
										   @PathVariable(name = "cellId") Long cellId,
										   @PathVariable(name = "taskId") Long taskId) {

		Task task = taskService.getById(taskId);
		TaskDTO result = taskMapper.taskToTaskDTO(task);

		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<TaskDTO>> getAllTasks(@PathVariable(name = "userId") Long userId,
													 @PathVariable(name = "cellId") Long cellId) {

		List<Task> taskList = taskService.getByUserCell(cellId);
		List<TaskDTO> result = taskMapper.listTaskToTaskDTO(taskList);

		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<TaskDTO> createTask(@PathVariable(name = "userId") Long userId,
											  @PathVariable(name = "cellId") Long cellId,
											  @RequestBody TaskDTO dto) {

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
	public ResponseEntity<Boolean> taskAddFiles(@PathVariable(name = "userId") Long userId,
												@PathVariable(name = "cellId") Long cellId,
												@PathVariable(name = "taskId") Long taskId,
												@RequestParam(name = "file") MultipartFile... files) {

		var result = taskService.addFilesForTask(taskId, Arrays.asList(files));

		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<Resource> getFileRelatedToTask(@PathVariable(name = "userId") Long userId,
														 @PathVariable(name = "cellId") Long cellId,
														 @PathVariable(name = "taskId") Long taskId,
														 @PathVariable(name = "fileId") Long fileId) {

		var file = taskAttachmentService.getByFileId(taskId, fileId);
		var fileName = file.getFileName();

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").
						contentType(MediaType.parseMediaType(file.getContentType()))
				.body(new ByteArrayResource(file.getContent()));
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

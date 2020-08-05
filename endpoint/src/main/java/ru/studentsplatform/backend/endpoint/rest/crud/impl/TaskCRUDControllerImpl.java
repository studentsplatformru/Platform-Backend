package ru.studentsplatform.backend.endpoint.rest.crud.impl;

import com.querydsl.core.types.Predicate;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
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
@EnableWebMvc
@RestController
@RequestMapping(TaskCRUDController.BASE_URL)
public class TaskCRUDControllerImpl implements TaskCRUDController {
	private final TaskMapper taskMapper;

	private final TaskServiceImpl taskService;

	private final TaskAttachmentService taskAttachmentService;

	public TaskCRUDControllerImpl(TaskMapper taskMapper, TaskServiceImpl taskService,
								  TaskAttachmentService taskAttachmentService) {
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
	public ResponseEntity<TaskDTO> create(TaskDTO dto) {
		var entity = taskMapper.taskDTOToTask(dto);
		var result = taskMapper.taskToTaskDTO(taskService.create(entity));
		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<TaskDTO> getById(Long id) {
		var result = taskMapper.taskToTaskDTO(taskService.getById(id));
		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<TaskDTO>> getAll() {
		var result = taskMapper.listTaskToTaskDTO(taskService.getAll());
		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<TaskDTO> update(TaskDTO updatedInstanceRequest, Long id) {
		var entity = taskMapper.taskDTOToTask(updatedInstanceRequest);
		var result = taskMapper.taskToTaskDTO(taskService.update(entity, id));
		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<Boolean> delete(Long id) {
		return ResponseEntity.ok(taskService.delete(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<TaskDTO>> getFiltered(Predicate predicate) {
		return ResponseEntity.ok(taskMapper.listTaskToTaskDTO(
				taskService.getByFilters(predicate)));
	}
}

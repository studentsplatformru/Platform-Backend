package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.dto.TaskDTO;
import ru.studentsplatform.backend.endpoint.mapper.TaskMapper;
import ru.studentsplatform.backend.endpoint.rest.TaskController;
import ru.studentsplatform.backend.service.crud.TaskAttachmentService;
import ru.studentsplatform.backend.service.crud.impl.TaskServiceImpl;
import ru.studentsplatform.backend.system.annotation.Profiled;

import java.util.Arrays;
import java.util.List;

@Profiled
@RestController
@RequestMapping("/task")
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
	 *{@inheritDoc}
	 */
	@Override
	public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO dto) {
		var task = taskMapper.taskDTOToTask(dto);
		task = taskService.create(task);

		var result = taskMapper.taskToTaskDTO(task);
		return ResponseEntity.ok(result);
	}

	@Override
	public ResponseEntity<TaskDTO> getById(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<List<TaskDTO>> getAll() {
		return null;
	}

	@Override
	public ResponseEntity<TaskDTO> update(TaskDTO updatedInstanceRequest, Long id) {
		return null;
	}

	@Override
	public ResponseEntity<Boolean> delete(Long id) {
		return null;
	}

	/**
	 *{@inheritDoc}
	 */
	@Override
	public ResponseEntity<Boolean> taskAddFiles(Long taskId, MultipartFile... files) {

		var result = taskService.addFilesForTask(taskId, Arrays.asList(files));

		return ResponseEntity.ok(result);
	}

	/**
	 *{@inheritDoc}
	 */
	@Override
	public ResponseEntity<Resource> getFileRelatedToTask(Long taskId, Long fileId) {

		var file = taskAttachmentService.getByFileId(taskId, fileId);
		var fileName = file.getFileName();

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").
						contentType(MediaType.parseMediaType(file.getContentType()))
				.body(new ByteArrayResource(file.getContent()));
	}

}

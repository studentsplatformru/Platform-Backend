package ru.studentsplatform.backend.endpoint.rest;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import ru.studentsplatform.backend.service.crud.TaskAttachmentService;
import ru.studentsplatform.backend.service.crud.impl.TaskServiceImpl;
import ru.studentsplatform.backend.system.annotation.Profiled;

/**
 * Контроллер, служащий для создания задач для студентов.
 * Позволяет создать сущность с необходимыми параметрами, а также прикреплять к ней файлы.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 26.07.2020
 */
@Profiled
@RestController
@RequestMapping("/task")
public class TaskController {
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
	public TaskController(TaskMapper taskMapper,
						  TaskServiceImpl taskService, TaskAttachmentService taskAttachmentService) {
		this.taskMapper = taskMapper;
		this.taskService = taskService;
		this.taskAttachmentService = taskAttachmentService;
	}

	/**
	 * Создает на основе полученных данных объект студенчекой задачи.
	 *
	 * @param dto Объект, содержащий данные, полученные от пользователя.
	 * @return Ответ со статусом 200(ok), содержащий сведения о сохраненной сущности.
	 */
	@PostMapping
	public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO dto) {
		var task = taskMapper.taskDTOToTask(dto);
		task = taskService.create(task);

		var result = taskMapper.taskToTaskDTO(task);
		return ResponseEntity.ok(result);
	}

	/**
	 * Позволяет прикреплять к студенческой задаче файлы с решением.
	 *
	 * @param taskId Идентификатор задачи, к которой будет прикреплено решение.
	 * @param files   Файлы, которые будут прикреплены к залдаче
	 * @return Ответ со статусом 200(ok),
	 * содержащий сведения о том, удачно ли прикреплены все полученные файлы.
	 */
	@PostMapping("/{id}/file")
	public ResponseEntity<Boolean> taskAddFiles(@PathVariable(name = "id") Long taskId,
												@RequestParam(name = "file") MultipartFile... files) {

		var result = taskService.addFilesForTask(taskId, files);

		return ResponseEntity.ok(result);
	}

	/**
	 * Загружает выбранный файл на компьютер.
	 * @param taskId Id задачи, к которой прикреплен файл для загрузки
	 * @param fileId Id файла дял загрузки
	 * @return тело веб-страницы
	 */
	@GetMapping("/{id}/file/{fileId}")
	public ResponseEntity<Resource> getFileRelatedToTask(@PathVariable(name = "id") Long taskId,
														 @PathVariable(name = "fileId") Long fileId) {

		var file = taskAttachmentService.getByFileId(taskId, fileId);
		var fileName = file.getFileName();

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").
						contentType(MediaType.parseMediaType(file.getContentType()))
				.body(new ByteArrayResource(file.getContent()));
	}

}

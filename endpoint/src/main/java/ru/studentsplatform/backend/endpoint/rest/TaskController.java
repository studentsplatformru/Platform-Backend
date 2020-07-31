package ru.studentsplatform.backend.endpoint.rest;

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
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.service.crud.TaskAttachmentService;
import ru.studentsplatform.backend.service.crud.impl.TaskServiceImpl;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.Arrays;
import java.util.List;

/**
 * Контроллер, служащий для создания задач для студентов.
 * Позволяет создать сущность с необходимыми параметрами, а также прикреплять к ней файлы.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 26.07.2020
 */
@Profiled
@RestController
@RequestMapping
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
	 *  Возвращает сведения о задаче с выбраным Id.
	 * @param userId Id пользователя, которому принадлежит задача
	 * @param cellId Id ячейки расписания пользователя, к которой прикреплена задача
	 * @param taskId Id задачи студента
	 * @return Ответ с кодом 200(ok), содержащий сведения о задаче
	 */
	@Transactional
	@GetMapping("user/{userId}/schedule/cell/{cellId}/task/{taskId}")
	public ResponseEntity<TaskDTO> gatTask(@PathVariable(name = "userId") Long userId,
										   @PathVariable(name = "cellId") Long cellId,
										   @PathVariable(name = "taskId") Long taskId) {

		Task task = taskService.getById(taskId);
		TaskDTO result = taskMapper.taskToTaskDTO(task);

		return ResponseEntity.ok(result);
	}

	/**
	 * Возвращает сведения о всех задачах, прикрепленных к ячейке расписания пользователя.
	 * @param userId Id пользователя, которому принадлежат задачи
	 * @param cellId Id ячейки расписания пользователя, к которой прикреплены задачи
	 * @return Ответ с кодом 200(ok), содержащий сведения о всех задачах, закрепленных за ячейкой
	 */
	@Transactional
	@GetMapping("user/{userId}/schedule/cell/{cellId}/tasks")
	public ResponseEntity<List<TaskDTO>> getAllTasks(@PathVariable(name = "userId") Long userId,
													 @PathVariable(name = "cellId") Long cellId) {

		List<Task> taskList = taskService.getByUserCell(cellId);
		List<TaskDTO> result = taskMapper.listTaskToTaskDTO(taskList);

		return ResponseEntity.ok(result);
	}

	/**
	 * Создает на основе полученных данных объект студенчекой задачи.
	 *
	 * @param userId Id пользователя, которому будет принадлежать задача
	 * @param cellId Id ячейки расписания пользователя, к которой бует прикреплена задача
	 * @param dto Объект, содержащий данные, полученные от пользователя.
	 * @return Ответ со статусом 200(ok), содержащий сведения о сохраненной сущности.
	 */
	@Transactional
	@PostMapping("user/{userId}/schedule/cell/{cellId}/task")
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
	 * Позволяет прикреплять к студенческой задаче файлы с решением.
	 *
	 * @param userId Id пользователя, которому принадлежит задача
	 * @param cellId Id ячейки расписания пользователя, к которой прикреплена задача
	 * @param taskId Идентификатор задачи, к которой будет прикреплено решение.
	 * @param files   Файлы, которые будут прикреплены к залдаче
	 * @return Ответ со статусом 200(ok),
	 * содержащий сведения о том, удачно ли прикреплены все полученные файлы.
	 */
	@Transactional
	@PostMapping("user/{userId}/schedule/cell/{cellId}/task/{taskId}/file")
	public ResponseEntity<Boolean> taskAddFiles(@PathVariable(name = "userId") Long userId,
												@PathVariable(name = "cellId") Long cellId,
												@PathVariable(name = "taskId") Long taskId,
												@RequestParam(name = "file") MultipartFile... files) {

		var result = taskService.addFilesForTask(taskId, Arrays.asList(files));

		return ResponseEntity.ok(result);
	}

	/**
	 * Загружает выбранный файл на компьютер.
	 * @param userId Id пользователя, которому принадлежит задача
	 * @param cellId Id ячейки расписания пользователя, к которой прикреплена задача
	 * @param taskId Id задачи, к которой прикреплен файл для загрузки
	 * @param fileId Id файла дял загрузки
	 * @return тело веб-страницы
	 */
	@Transactional
	@GetMapping("user/{userId}/schedule/cell/{cellId}/task/{taskId}/file/{fileId}")
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

}

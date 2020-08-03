package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.dto.university.TaskDTO;

import java.util.List;

/**
 * Контроллер, служащий для создания задач для студентов.
 * Позволяет создать сущность с необходимыми параметрами, а также прикреплять к ней файлы.
 */
public interface TaskCRUDController extends AbstractCRUDController<TaskDTO> {

	String BASE_URL = AbstractCRUDController.BASE_URL + "/task";

	/**
	 * Позволяет прикреплять к студенческой задаче файлы с решением.
	 *
	 * @param userId Id пользователя, которому принадлежит задача
	 * @param cellId Id ячейки расписания пользователя, к которой прикреплена задача
	 * @param taskId Идентификатор задачи, к которой будет прикреплено решение.
	 * @param files  Файлы, которые будут прикреплены к залдаче
	 * @return Ответ со статусом 200(ok),
	 * содержащий сведения о том, удачно ли прикреплены все полученные файлы.
	 */
	@PostMapping("user/{userId}/schedule/cell/{cellId}/task/{taskId}/file")
	ResponseEntity<Boolean> taskAddFiles(@PathVariable(name = "userId") Long userId,
										 @PathVariable(name = "cellId") Long cellId,
										 @PathVariable(name = "taskId") Long taskId,
										 @RequestParam(name = "file") MultipartFile... files);

	/**
	 * Загружает выбранный файл на компьютер.
	 *
	 * @param userId Id пользователя, которому принадлежит задача
	 * @param cellId Id ячейки расписания пользователя, к которой прикреплена задача
	 * @param taskId Id задачи, к которой прикреплен файл для загрузки
	 * @param fileId Id файла дял загрузки
	 * @return тело веб-страницы
	 */
	@GetMapping("user/{userId}/schedule/cell/{cellId}/task/{taskId}/file/{fileId}")
	ResponseEntity<Resource> getFileRelatedToTask(@PathVariable(name = "userId") Long userId,
												  @PathVariable(name = "cellId") Long cellId,
												  @PathVariable(name = "taskId") Long taskId,
												  @PathVariable(name = "fileId") Long fileId);

	/**
	 * Создает на основе полученных данных объект студенчекой задачи.
	 *
	 * @param userId Id пользователя, которому будет принадлежать задача
	 * @param cellId Id ячейки расписания пользователя, к которой бует прикреплена задача
	 * @param dto    Объект, содержащий данные, полученные от пользователя.
	 * @return Ответ со статусом 200(ok), содержащий сведения о сохраненной сущности.
	 */
	@PostMapping("user/{userId}/schedule/cell/{cellId}/task")
	ResponseEntity<TaskDTO> createTask(@PathVariable(name = "userId") Long userId,
									   @PathVariable(name = "cellId") Long cellId,
									   @RequestBody TaskDTO dto);

	/**
	 * Возвращает сведения о всех задачах, прикрепленных к ячейке расписания пользователя.
	 *
	 * @param userId Id пользователя, которому принадлежат задачи
	 * @param cellId Id ячейки расписания пользователя, к которой прикреплены задачи
	 * @return Ответ с кодом 200(ok), содержащий сведения о всех задачах, закрепленных за ячейкой
	 */
	@GetMapping("user/{userId}/schedule/cell/{cellId}/tasks")
	ResponseEntity<List<TaskDTO>> getAllTasks(@PathVariable(name = "userId") Long userId,
											  @PathVariable(name = "cellId") Long cellId);

	/**
	 * Возвращает сведения о задаче с выбраным Id.
	 *
	 * @param userId Id пользователя, которому принадлежит задача
	 * @param cellId Id ячейки расписания пользователя, к которой прикреплена задача
	 * @param taskId Id задачи студента
	 * @return Ответ с кодом 200(ok), содержащий сведения о задаче
	 */
	@GetMapping("user/{userId}/schedule/cell/{cellId}/task/{taskId}")
	ResponseEntity<TaskDTO> getTask(@PathVariable(name = "userId") Long userId,
									@PathVariable(name = "cellId") Long cellId,
									@PathVariable(name = "taskId") Long taskId);

	@GetMapping("user/{userId}/schedule/cell/{cellId}/task/{taskId}/isDone/{isDone}")
	ResponseEntity<List<TaskDTO>> getIsDoneTaskForUser(@PathVariable(name = "userId") Long userId,
													   @PathVariable(name = "cellId") Long cellId,
													   @PathVariable(name = "taskId") Long taskId,
													   @PathVariable(name = "isDone") Boolean isDone);


	@GetMapping("user/{userId}/schedule/cell/{cellId}/task/{taskId}/semester/{semester}")
	ResponseEntity<List<TaskDTO>> getTaskBySemesterForUser(@PathVariable(name = "userId") Long userId,
														   @PathVariable(name = "cellId") Long cellId,
														   @PathVariable(name = "taskId") Long taskId,
														   @PathVariable(name = "semester") Long semester);

	@GetMapping("user/{userId}/schedule/cell/{cellId}/task/{taskId}/subject/{subjectId}")
	ResponseEntity<List<TaskDTO>> getTaskBySubjectForUser(@PathVariable(name = "userId") Long userId,
														  @PathVariable(name = "cellId") Long cellId,
														  @PathVariable(name = "taskId") Long taskId,
														  @PathVariable(name = "subjectId") Long subjectId);
}


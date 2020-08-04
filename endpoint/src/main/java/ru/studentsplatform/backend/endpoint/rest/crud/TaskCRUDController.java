package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.cglib.core.Predicate;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.dto.university.TaskDTO;
import ru.studentsplatform.backend.entities.model.university.Task;

import java.time.OffsetDateTime;
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
	 * @param taskId Идентификатор задачи, к которой будет прикреплено решение.
	 * @param files  Файлы, которые будут прикреплены к залдаче
	 * @return Ответ со статусом 200(ok),
	 * содержащий сведения о том, удачно ли прикреплены все полученные файлы.
	 */
	@PostMapping("/{taskId}/file")
	ResponseEntity<Boolean> taskAddFiles(@PathVariable(name = "taskId") Long taskId,
										 @RequestParam(name = "file") MultipartFile... files);

	/**
	 * Загружает выбранный файл на компьютер.
	 *
	 * @param taskId Id задачи, к которой прикреплен файл для загрузки
	 * @param fileId Id файла дял загрузки
	 * @return тело веб-страницы
	 */
	@GetMapping("/{taskId}/file/{fileId}")
	ResponseEntity<Resource> getFileRelatedToTask(@PathVariable(name = "taskId") Long taskId,
												  @PathVariable(name = "fileId") Long fileId);

	/**
	 * Создает на основе полученных данных объект студенчекой задачи.
	 *
	 * @param cellId Id ячейки расписания пользователя, к которой бует прикреплена задача
	 * @param dto    Объект, содержащий данные, полученные от пользователя.
	 * @return Ответ со статусом 200(ok), содержащий сведения о сохраненной сущности.
	 */
	@PostMapping("/cell/{cellId}/create")
	ResponseEntity<TaskDTO> createTask(@PathVariable(name = "cellId") Long cellId,
									   @RequestBody TaskDTO dto);

	/**
	 * Возвращает сведения о всех задачах, прикрепленных к ячейке расписания пользователя.
	 *
	 * @param cellId Id ячейки расписания пользователя, к которой прикреплены задачи
	 * @return Ответ с кодом 200(ok), содержащий сведения о всех задачах, закрепленных за ячейкой
	 */
	@GetMapping("/cell/{cellId}")
	ResponseEntity<List<TaskDTO>> getAllTasksForCell(@PathVariable(name = "cellId") Long cellId);

	/**
	 * Возвращает сведения о задаче с выбраным Id.
	 *
	 * @param taskId Id задачи студента
	 * @return Ответ с кодом 200(ok), содержащий сведения о задаче
	 */
	@GetMapping("/{taskId}")
	ResponseEntity<TaskDTO> getTask(@PathVariable(name = "taskId") Long taskId);

	/**
	 * Возвращает сведения о задачах для конкретного пользователя
	 * по степени их завершенности.
	 *
	 * @param userId Id пользователя, которому принадлежит задача
	 * @param isDone Завершена ли задача
	 * @return Ответ с кодом 200(ok), содержащий сведения о задачах
	 */
	@GetMapping("/user/{userId}/isDone/{isDone}")
	ResponseEntity<List<TaskDTO>> getByDoneTaskForUser(@PathVariable(name = "userId") Long userId,
													   @PathVariable(name = "isDone") Boolean isDone);

	/**
	 * Возвращает сведения о задачах для конкретного пользователя
	 * по семестру.
	 *
	 * @param userId Id пользователя, которому принадлежит задача
	 * @param semester номер семестра
	 * @return Ответ с кодом 200(ok), содержащий сведения о задачах
	 */
	@GetMapping("/user/{userId}/semester/{semester}")
	ResponseEntity<List<TaskDTO>> getTaskBySemesterForUser(@PathVariable(name = "userId") Long userId,
														   @PathVariable(name = "semester") Integer semester);

	/**
	 * Возвращает сведения о задачах для конкретного пользователя
	 * по предмету.
	 *
	 * @param userId Id пользователя, которому принадлежит задача
	 * @param subjectId Id предмета
	 * @return Ответ с кодом 200(ok), содержащий сведения о задачах
	 */
	@GetMapping("/user/{userId}/subject/{subjectId}")
	ResponseEntity<List<TaskDTO>> getTaskBySubjectForUser(@PathVariable(name = "userId") Long userId,
														  @PathVariable(name = "subjectId") Long subjectId);

	/**
	 * Возвращает сведения о задачах для группы студентов.
	 *
	 * @param subjectId Id предмета
	 * @param groupID Id группы студентов
	 * @return Ответ с кодом 200(ok), содержащий сведения о задачах
	 */
	@GetMapping("/subject/{subjectId}/group/{groupId}")
	ResponseEntity<List<TaskDTO>> getTaskByGroup(@PathVariable(name = "subjectId") Long subjectId,
												 @PathVariable(name = "groupId") Long groupID);

	/**
	 * Возвращает список всех задач данного пользователя.
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @return Ответ с кодом 200(ok), содержащий список задач и свединия о них.
	 */
	@GetMapping("/user/{userId}/getTasks")
	ResponseEntity<List<TaskDTO>> getTaskByStartEndTimeForUser(
			@PathVariable(name = "userId") Long userId,
			@RequestParam(value = "start-time", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
					OffsetDateTime startTime,
			@RequestParam(value = "end-time", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
					OffsetDateTime endTime);

	@GetMapping("/filter")
	ResponseEntity getFiltered(@QuerydslPredicate(root = Task.class) Predicate predicate, Pageable pageable);

}


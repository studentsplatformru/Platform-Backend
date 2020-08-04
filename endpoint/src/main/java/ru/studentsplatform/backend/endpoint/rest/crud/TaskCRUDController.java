package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.dto.university.TaskDTO;

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
	 * Возвращает сведения о задаче с выбраным Id.
	 *
	 * @param taskId Id задачи студента
	 * @return Ответ с кодом 200(ok), содержащий сведения о задаче
	 */
	@GetMapping("/{taskId}")
	ResponseEntity<TaskDTO> getTask(@PathVariable(name = "taskId") Long taskId);

	/**
	 * Найти задачи с учетом заданных фильтрующих параметров.
	 *
	 * @param userId Id пользователя, которому принадежит задача
	 * @param usrCellId Id ячейки полшьзовательского расписания
	 * @param subjectId Id предмета, для которого создана задача
	 * @param groupId Id группы студентов, в которой сотоит владелец задачи
	 * @param semester Семестр, в который была получена задача
	 * @param startTime Временные рамки получения задач: начало
	 * @param endTime Временные рамки получения задачи: конец
	 * @return Ответ, содержащий Лист найденных задач
	 */
	@GetMapping("/filter")
	ResponseEntity<List<TaskDTO>> getFiltered(@RequestParam(name = "userId", required = false) Long userId,
											  @RequestParam(name = "userCellId", required = false) Long usrCellId,
											  @RequestParam(value = "subjectId", required = false) Long subjectId,
											  @RequestParam(name = "groupId", required = false) Long groupId,
											  @RequestParam(name = "semester", required = false) Integer semester,
											  @RequestParam(value = "startTime", required = false)
											  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
													  OffsetDateTime startTime,
											  @RequestParam(value = "endTime", required = false)
											  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
													  OffsetDateTime endTime);

}


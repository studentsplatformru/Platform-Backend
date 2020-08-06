package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.dto.TaskFilterDTO;
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
	 * Возвращает сведения о задачах с учетом примененных фильтров.
	 *
	 * @param taskFilterDTO Предикат,содержащий в себе все выбранные фильтры
	 *                  Фильтры указываются следующим образом:
	 *                  Если необходимо вывести задачи с определенным значением поля,
	 *                  то указываем в параметрах поиска %имя_поля%=%значение_поля%
	 *                  Пример: /filter?id=1 - выводит все задачи с id = 1.
	 *                  Имя поля соответствует имени поля в Task(сущность)
	 *                  Если поле находится в сущности, которая связана с Task, то
	 *                  необходимо указать путь до этого поля
	 *                  Пример: /filter?scheduleUserCell.id=1 - выводит все задачи привязанные к
	 *                  ячейке расписания с id=1.
	 *                  Чтобы вывести все задачи, привязанные к ячейкам расписания, дата занятий которых
	 *                  поздее заданной даты, необходимо указать /filter?scheduleUserCell.scheduleCell.startClass=%дата%
	 *                  Раньше заданной даты: /filter?scheduleUserCell.scheduleCell.endClass=%дата%
	 *                  Между двумя датами:
	 *                  /filter?scheduleUserCell.scheduleCell.startClass=%нижняя_дата%&
	 *                  scheduleUserCell.scheduleCell.endClass=%верхняя_дата%
	 * @return Ответ с кодом 200(ok), содержащий сведения о задачах
	 */
	@GetMapping("/filter")
	ResponseEntity<List<TaskDTO>> getFiltered(@RequestBody TaskFilterDTO taskFilterDTO);
}


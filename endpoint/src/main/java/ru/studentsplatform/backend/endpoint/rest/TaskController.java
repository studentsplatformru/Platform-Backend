package ru.studentsplatform.backend.endpoint.rest;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.dto.TaskDTO;

import javax.transaction.Transactional;

/**
 * Контроллер, служащий для создания задач для студентов.
 * Позволяет создать сущность с необходимыми параметрами, а также прикреплять к ней файлы.
 */
public interface TaskController extends AbstractController<TaskDTO> {

    /**
     * Создает на основе полученных данных объект студенчекой задачи.
     *
     * @param dto Объект, содержащий данные, полученные от пользователя.
     * @return Ответ со статусом 200(ok), содержащий сведения о сохраненной сущности.
     */
    @Override
    ResponseEntity<TaskDTO> create(TaskDTO dto);

    /**
     * Позволяет прикреплять к студенческой задаче файлы с решением.
     *
     * @param taskId Идентификатор задачи, к которой будет прикреплено решение.
     * @param files   Файлы, которые будут прикреплены к залдаче
     * @return Ответ со статусом 200(ok),
     * содержащий сведения о том, удачно ли прикреплены все полученные файлы.
     */
    @PostMapping("/{id}/file")
    ResponseEntity<Boolean> taskAddFiles(@PathVariable(name = "id") Long taskId,
                                                @RequestParam MultipartFile... files);
    /**
     * Загружает выбранный файл на компьютер.
     * @param taskId Id задачи, к которой прикреплен файл для загрузки
     * @param fileId Id файла дял загрузки
     * @return тело веб-страницы
     */
    @Transactional
    @GetMapping("/{id}/file/{fileId}")
    ResponseEntity<Resource> getFileRelatedToTask(@PathVariable(name = "id") Long taskId,
                                                         @PathVariable(name = "fileId") Long fileId);
}

package ru.studentsplatform.backend.endpoint.rest.taskCreation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import ru.studentsplatform.backend.domain.dto.TaskDTO;
import ru.studentsplatform.backend.domain.repository.TaskAttachmentRepository;
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.domain.mapper.TaskMapper;
import ru.studentsplatform.backend.entities.model.utility.TaskAttachment;
import ru.studentsplatform.backend.service.crud.impl.TaskServiceImpl;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.service.exception.core.BusinessException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Контроллер, служащий для создания задач для студентов.
 * Позволяет создать сущность с необходимыми параметрами, а также прикреплять к ней файлы.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 26.07.2020
 */
@RestController
@RequestMapping("/task")
public class TaskCreationController {

    private final Logger logger = LoggerFactory.getLogger("taskCreationLogger");

    private final TaskMapper mapper;

    private final TaskServiceImpl service;

    private final TaskAttachmentRepository attachmentRepository;

    public TaskCreationController(TaskMapper mapper, TaskServiceImpl service,
                                  TaskAttachmentRepository attachmentRepository) {
        this.mapper = mapper;
        this.service = service;
        this.attachmentRepository = attachmentRepository;
    }

    /**
     * Создает на основе полученных данных объект студенчекой задачи.
     * Если задачу удается прикрепить к какой-либо ячейке расписания -
     * объект успешно сохраняется в БД, в противном случае
     * выбрасывается бизнесс-исключение и объект не сохранияется в базе.
     *
     * @param dto Объект, содержащий данные, полученные от пользователя.
     * @return Ответ со статусом 200(ok), содержащий сведения о сохраненной сущности.
     */
    @PostMapping
    public ResponseEntity createTask(@RequestBody TaskDTO dto) {

        Task task = mapper.taskDTOToTask(dto);

        if (task.getScheduleUserCell().getId() == null) {
            throw new BusinessException(ServiceExceptionReason.SCHEDULE_CELL_NOT_FOUND);
        }

        task = service.create(task);

        return ResponseEntity.ok(mapper.taskToTaskDTO(task));
    }

    /**
     * Позволяет прикреплять к студенческой задаче файлы с решением.
     * @param id Идентификатор задачи, к которой будет прикреплено решение.
     * @param files Массив файлов, которые будут прикреплены к залдаче
     * @return Ответ со статусом 200(ok),
     * содержащий сведения о задаче с прикрепленными к ней файлами.
     */
    @PostMapping("/{id}/file")
    public ResponseEntity addFiles(@PathVariable Long id, @RequestBody File... files) {

        Task task = service.getById(id);

            for (File file: files) {
                try {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

                    TaskAttachment attachment = new TaskAttachment();

                    attachment.setFileName(file.getName());
                    attachment.setFileContent(bufferedInputStream.readAllBytes());
                    attachment.setTask(task);
                    task.getAttachments().
                            add(attachmentRepository.saveAndFlush(attachment));

                } catch (IOException e) {
                    logger.error("Error occurred while adding task attachments!");
                }
            }

        return ResponseEntity.ok(mapper.taskToTaskDTO(task));
    }

}

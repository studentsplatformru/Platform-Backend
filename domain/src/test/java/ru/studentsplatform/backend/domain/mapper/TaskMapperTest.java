package ru.studentsplatform.backend.domain.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.domain.dto.TaskDTO;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleUserCell;
import ru.studentsplatform.backend.entities.model.university.Task;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест методов mapper и способности его переносить поля и объекты из сущности в DTO и обратно.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 26.07.2020
 */
@ExtendWith(MockitoExtension.class)
class TaskMapperTest {

    private TaskMapperImpl mapper = new TaskMapperImpl();

    /**
     * Проверка того, что поля сущности и DTO, созданного при помощи mapper, совпадают.
     */
    @Test
    void taskToTaskDTO() {
        Task entity = new Task();
        ScheduleUserCell cell = new ScheduleUserCell();

        cell.setId(2L);

        entity.setId(1L);
        entity.setCreatedBy("Me");
        entity.setModifiedBy("AlsoMe");
        entity.setTaskName("testName");
        entity.setDone(false);
        entity.setDeadLine(new Date());
        entity.setMark(4);
        entity.setScheduleUserCell(cell);

        TaskDTO dto = mapper.taskToTaskDTO(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
        assertEquals(dto.getModifiedBy(), entity.getModifiedBy());
        assertEquals(dto.getTaskName(), entity.getTaskName());
        assertEquals(dto.getMark(), entity.getMark());
        assertEquals(dto.getDeadLine(), entity.getDeadLine());
        assertEquals(dto.isDone(), entity.isDone());
        assertEquals(dto.getScheduleUserCellId(), entity.getScheduleUserCell().getId());

    }

    /**
     * Проверка того, что поля DTO и сущности, созданной при помощи mapper, совпадают.
     */
    @Test
    void taskDTOToTask() {
        TaskDTO dto = new TaskDTO();

        dto.setId(1L);
        dto.setCreatedBy("Me");
        dto.setModifiedBy("AlsoMe");
        dto.setTaskName("testName");
        dto.setDone(false);
        dto.setDeadLine(new Date());
        dto.setMark(4);
        dto.setScheduleUserCellId(2L);

        Task entity = mapper.taskDTOToTask(dto);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
        assertEquals(dto.getModifiedBy(), entity.getModifiedBy());
        assertEquals(dto.getTaskName(), entity.getTaskName());
        assertEquals(dto.getMark(), entity.getMark());
        assertEquals(dto.getDeadLine(), entity.getDeadLine());
        assertEquals(dto.isDone(), entity.isDone());
        assertEquals(dto.getScheduleUserCellId(), entity.getScheduleUserCell().getId());
    }

    /**
     * Проверка того, что mapper корректо прозводит конвертацию листа сущностей в лист DTO,
     * и корректно переносит их поля.
     */
    @Test
    void listTaskToTaskDTO() {
        List<Task> taskList = new LinkedList<>();
        Task testTask = new Task();
        testTask.setId(3L);
        taskList.add(testTask);
        taskList.add(testTask);
        List<TaskDTO> taskDTOS = mapper.listTaskToTaskDTO(taskList);
        assertEquals(3L,taskDTOS.get(0).getId());
        assertEquals(3L,taskDTOS.get(1).getId());
    }

    /**
     * Проверка того, что mapper корректо прозводит конвертацию листа DTO в лист сущностей,
     * и корректно переносит их поля.
     */
    @Test
    void listTaskDTOToTask() {
        List<TaskDTO> taskDTOS = new LinkedList<>();
        TaskDTO testTaskDTO = new TaskDTO();
        testTaskDTO.setId(5L);
        taskDTOS.add(testTaskDTO);
        taskDTOS.add(testTaskDTO);
        List<Task> tasks = mapper.listTaskDTOToTask(taskDTOS);
        assertEquals(5L,tasks.get(0).getId());
        assertEquals(5L,tasks.get(1).getId());
    }
}
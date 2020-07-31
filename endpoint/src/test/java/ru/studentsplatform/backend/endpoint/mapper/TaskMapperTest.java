package ru.studentsplatform.backend.endpoint.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.domain.dto.utility.TaskAttachmentDTO;
import ru.studentsplatform.backend.domain.dto.university.TaskDTO;
import ru.studentsplatform.backend.endpoint.mapper.TaskAttachmentMapperImpl;
import ru.studentsplatform.backend.endpoint.mapper.TaskMapperImpl;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleUserCell;
import ru.studentsplatform.backend.entities.model.university.Task;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест методов mapper и способности его переносить поля и объекты из сущности в DTO и обратно.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 26.07.2020
 */
@ExtendWith(MockitoExtension.class)
class TaskMapperTest {

	@Mock
	private TaskAttachmentMapperImpl attachmentMapper;

	@InjectMocks
	private TaskMapperImpl mapper;


	/**
	 * Проверка того, что поля сущности и DTO, созданного при помощи mapper, совпадают.
	 */
	@Test
	void taskToTaskDTOTest() {
		Task entity = new Task();
		ScheduleUserCell cell = new ScheduleUserCell();

		cell.setId(2L);

		entity.setId(1L);
		entity.setCreatedBy("Me");
		entity.setModifiedBy("AlsoMe");
		entity.setTaskName("testName");
		entity.setDone(false);
		entity.setDeadLine(OffsetDateTime.of(LocalDateTime.of(2017, 5, 12, 5, 45, 5),
                ZoneOffset.ofHoursMinutes(6, 0)));
		entity.setMark(4);
		entity.setScheduleUserCell(cell);

		TaskDTO dto = mapper.taskToTaskDTO(entity);
		assertEquals(dto.getId(), entity.getId());
		assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
		assertEquals(dto.getModifiedBy(), entity.getModifiedBy());
		assertEquals(dto.getTaskName(), entity.getTaskName());
		assertEquals(dto.getMark(), entity.getMark());
		assertEquals(dto.getDeadLine(), entity.getDeadLine());
		assertEquals(dto.getIsDone(), entity.getDone());
		assertEquals(dto.getScheduleUserCellId(), entity.getScheduleUserCell().getId());

	}

	/**
	 * Проверка того, что поля DTO и сущности, созданной при помощи mapper, совпадают.
	 */
	@Test
	void taskDTOToTaskTest() {
		TaskDTO dto = new TaskDTO();
		LinkedList<TaskAttachmentDTO> attachmentDTOS = new LinkedList<>();
		TaskAttachmentDTO attachmentDTO = new TaskAttachmentDTO();
		attachmentDTOS.add(attachmentDTO);

		dto.setId(1L);
		dto.setCreatedBy("Me");
		dto.setModifiedBy("AlsoMe");
		dto.setTaskName("testName");
		dto.setIsDone(false);
		dto.setDeadLine(OffsetDateTime.of(LocalDateTime.of(2017, 5, 12, 5, 45, 5),
                ZoneOffset.ofHoursMinutes(6, 0)));
		dto.setMark(4);
		dto.setScheduleUserCellId(2L);
		dto.setAttachments(attachmentDTOS);


		Task entity = mapper.taskDTOToTask(dto);
		assertEquals(dto.getId(), entity.getId());
		assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
		assertEquals(dto.getModifiedBy(), entity.getModifiedBy());
		assertEquals(dto.getTaskName(), entity.getTaskName());
		assertEquals(dto.getMark(), entity.getMark());
		assertEquals(dto.getDeadLine(), entity.getDeadLine());
		assertEquals(dto.getIsDone(), entity.getDone());
		assertEquals(dto.getScheduleUserCellId(), entity.getScheduleUserCell().getId());
	}

	/**
	 * Проверка того, что mapper корректо прозводит конвертацию листа сущностей в лист DTO,
	 * и корректно переносит их поля.
	 */
	@Test
	void listTaskToTaskDTOTest() {
		List<Task> taskList = new LinkedList<>();
		Task testTask = new Task();
		testTask.setId(3L);
		taskList.add(testTask);
		taskList.add(testTask);
		List<TaskDTO> taskDTOS = mapper.listTaskToTaskDTO(taskList);
		assertEquals(3L, taskDTOS.get(0).getId());
		assertEquals(3L, taskDTOS.get(1).getId());
	}

	/**
	 * Проверка того, что mapper корректо прозводит конвертацию листа DTO в лист сущностей,
	 * и корректно переносит их поля.
	 */
	@Test
	void listTaskDTOToTaskTest() {
		List<TaskDTO> taskDTOS = new LinkedList<>();
		TaskDTO testTaskDTO = new TaskDTO();
		testTaskDTO.setId(5L);
		taskDTOS.add(testTaskDTO);
		taskDTOS.add(testTaskDTO);
		List<Task> tasks = mapper.listTaskDTOToTask(taskDTOS);
		assertEquals(5L, tasks.get(0).getId());
		assertEquals(5L, tasks.get(1).getId());
	}
}
package ru.studentsplatform.backend.service.crud.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.pojo.filters.TaskFilterPOJO;
import ru.studentsplatform.backend.domain.repository.TaskRepository;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleUserCell;
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.entities.model.utility.TaskAttachment;
import ru.studentsplatform.backend.service.crud.TaskAttachmentService;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Тест каждого метода класса TaskServiceImpl
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 26.07.2020
 */
@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

	@Mock
	private TaskRepository taskRepository;

	@Mock
	private TaskAttachmentService taskAttachmentService;

	@InjectMocks
	private TaskServiceImpl taskService;

	/**
	 * Проверка того, что метод create возвращает созданную задачу.
	 * Сымитировано поведение saveAndFlush.
	 */
	@Test
	void createTest() {
		var task = mock(Task.class);
		var scheduleUserCell = mock(ScheduleUserCell.class);

		doReturn(task).when(taskRepository).save(task);
		doReturn(scheduleUserCell).when(task).getScheduleUserCell();
		doReturn(1L).when(scheduleUserCell).getId();

		var result = taskService.create(task);
		assertEquals(task, result);
	}

	/**
	 * Проверка того, что метод findById возвращает созданную задачу
	 * и кидает NoSuchElementException при её отсутствии.
	 */
	@Test
	void getByIdTest() {
		Task task = new Task();

		when(taskRepository.findById(2L)).thenReturn(java.util.Optional.of(task));

		assertEquals(taskRepository.findById(2L).orElseThrow(), taskService.getById(2L));
		assertThrows(NoSuchElementException.class, () -> taskService.getById(3L));
	}

	/**
	 * Проверка того, что метод getAll выводит результат метода findAll репозитория.
	 */
	@Test
	void getAllTest() {
		assertEquals(taskService.getAll(), taskRepository.findAll());
	}

	/**
	 * Проверка того, что метод update возвращает обновлённую задачу с Id,
	 * заданным в параметре.
	 */
	@Test
	void updateTest() {
		Task newTask = new Task();

		when(taskRepository.saveAndFlush(newTask)).thenReturn(newTask);
		assertEquals(newTask, taskService.update(newTask, 3L));
		assertEquals(3L, taskService.update(newTask, 3L).getId());
	}

	/**
	 * Проверка того, что метод delete возвращает true, если
	 * EmptyResultDataAccessException не был брошен,
	 * и возвращает false в обратном случае.
	 */
	@Test
	void deleteTest() {
		assertTrue(taskService.delete(anyLong()));

		doThrow(EmptyResultDataAccessException.class).when(taskRepository).deleteById(anyLong());
		assertFalse(taskService.delete(anyLong()));
	}

	/**
	 * Проверка того, что при наличии файла метод возвращает true,
	 * в впротивном случае бросит бизнесс-исключение.
	 */
	@Test
	void addFilesTest() {
		var task = mock(Task.class);
		var file = mock(MultipartFile.class);
		var attachment = mock(TaskAttachment.class);
		doReturn(task).when(taskRepository).getOne(anyLong());
		doReturn(attachment).when(taskAttachmentService).createByFile(task, file);
		assertTrue(taskService.addFilesForTask(2L, Arrays.asList(file)));
		MultipartFile nullFile = null;
		assertThrows(BusinessException.class,
				() -> taskService.addFilesForTask(3L, Arrays.asList(nullFile)));
		assertThrows(BusinessException.class,
				() -> taskService.addFilesForTask(3L, null));
	}

	/**
	 * Test for getBYFilters method
	 *
	 * @see ru.studentsplatform.backend.service.crud.TaskService#getByFilters(TaskFilterPOJO)
	 */
	@Test
	void getByFiltersTest() {
		TaskFilterPOJO taskFilterPOJO = new TaskFilterPOJO();
		taskFilterPOJO.setGroupId(null);
		List<Task> task = new LinkedList<Task>();
		doReturn(task).when(taskRepository).findAll(Mockito.any(BooleanBuilder.class));
		Assert.assertEquals(task, taskService.getByFilters(taskFilterPOJO));
	}
}
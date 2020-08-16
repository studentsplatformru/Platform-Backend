package ru.studentsplatform.backend.service.crud.impl;

import com.querydsl.core.BooleanBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.domain.pojo.filters.ScheduleUserCellFilterPOJO;
import ru.studentsplatform.backend.domain.repository.ScheduleUserCellRepository;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleUserCell;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScheduleUserCellServiceImplTest {
	@Mock
	private ScheduleUserCellRepository scheduleUserCellRepository;

	@InjectMocks
	private ScheduleUserCellServiceImpl scheduleUserCellService;

	/**
	 * Проверка того, что метод create возвращает созданную ячейку студенческого расписания.
	 * Сымитировано поведение saveAndFlush.
	 */
	@Test
	void createTest() {
		var scheduleUserCell = mock(ScheduleUserCell.class);
		doReturn(scheduleUserCell).when(scheduleUserCellRepository).save(scheduleUserCell);
		var result = scheduleUserCellService.create(scheduleUserCell);
		assertEquals(scheduleUserCell, result);
	}

	/**
	 * Проверка того, что метод findById возвращает созданную ячейку студенческого расписания
	 * и кидает NoSuchElementException при её отсутствии.
	 */
	@Test
	void getByIdTest() {
		ScheduleUserCell scheduleUserCell = new ScheduleUserCell();

		when(scheduleUserCellRepository.findById(2L)).thenReturn(java.util.Optional.of(scheduleUserCell));

		assertEquals(scheduleUserCellRepository.findById(2L).orElseThrow(), scheduleUserCellService.getById(2L));
		assertThrows(BusinessException.class, () -> scheduleUserCellService.getById(3L));
	}

	/**
	 * Проверка того, что метод getAll выводит результат метода findAll репозитория.
	 */
	@Test
	void getAllTest() {
		assertEquals(scheduleUserCellService.getAll(), scheduleUserCellRepository.findAll());
	}

	/**
	 * Проверка того, что метод update возвращает обновлённую ячейку студенческого расписания с Id,
	 * заданным в параметре.
	 */
	@Test
	void updateTest() {
		ScheduleUserCell newScheduleUserCell = new ScheduleUserCell();

		doReturn(newScheduleUserCell).when(scheduleUserCellRepository).saveAndFlush(newScheduleUserCell);
		doReturn(true).when(scheduleUserCellRepository).existsById(3L);
		assertEquals(newScheduleUserCell, scheduleUserCellService.update(newScheduleUserCell, 3L));
		assertEquals(3L, scheduleUserCellService.update(newScheduleUserCell, 3L).getId());
	}

	/**
	 * Проверка того, что метод delete возвращает true, если
	 * EmptyResultDataAccessException не был брошен,
	 * и возвращает false в обратном случае.
	 */
	@Test
	void deleteTest() {
		assertTrue(scheduleUserCellService.delete(anyLong()));

		doThrow(EmptyResultDataAccessException.class).when(scheduleUserCellRepository).deleteById(anyLong());
		assertFalse(scheduleUserCellService.delete(anyLong()));
	}

	@Test
	void getFilteredTest() {
		ScheduleUserCellFilterPOJO scheduleUserCellFilterDTO = new ScheduleUserCellFilterPOJO();
		scheduleUserCellFilterDTO.setDisciplineId(null);
		List<ScheduleUserCell> scheduleUserCells = new LinkedList<>();
		doReturn(scheduleUserCells).when(scheduleUserCellRepository).findAll(Mockito.any(BooleanBuilder.class));
		Assert.assertEquals(scheduleUserCells, scheduleUserCellService.getFiltered(scheduleUserCellFilterDTO));
	}

	@Test
	void getFilteredPercentageTest() {
		ScheduleUserCellFilterPOJO scheduleUserCellFilterPOJO = mock(ScheduleUserCellFilterPOJO.class);

		List<ScheduleUserCell> allCells = new LinkedList<>();
		allCells.add(new ScheduleUserCell());
		allCells.add(new ScheduleUserCell());

		List<ScheduleUserCell> cellWithPresence = new LinkedList<>();
		cellWithPresence.add(new ScheduleUserCell());

		doReturn(allCells, cellWithPresence).when(scheduleUserCellRepository).findAll(Mockito.any(BooleanBuilder.class));
		var result = scheduleUserCellService.getFilteredPercentage(scheduleUserCellFilterPOJO);
		assertEquals(result, "50.0%");
	}
}

package ru.studentsplatform.backend.service.crud.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.domain.repository.ScheduleCellRepository;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleCell;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScheduleCellServiceImplTest {
    @Mock
    private ScheduleCellRepository scheduleCellRepository;

    @InjectMocks
    private ScheduleCellServiceImpl scheduleCellService;

    /**
     * Проверка того, что метод create возвращает созданную ячейку расписания.
     * Сымитировано поведение saveAndFlush.
     */
    @Test
    void createTest() {
        var scheduleCell = mock(ScheduleCell.class);
        doReturn(scheduleCell).when(scheduleCellRepository).save(scheduleCell);
        var result = scheduleCellService.create(scheduleCell);
        assertEquals(scheduleCell, result);
    }

    /**
     * Проверка того, что метод findById возвращает созданную ячейку расписания
     * и кидает NoSuchElementException при её отсутствии.
     */
    @Test
    void getByIdTest() {
        ScheduleCell scheduleCell = new ScheduleCell();

        when(scheduleCellRepository.findById(2L)).thenReturn(java.util.Optional.of(scheduleCell));

        assertEquals(scheduleCellRepository.findById(2L).orElseThrow(), scheduleCellService.getById(2L));
        assertThrows(BusinessException.class, () -> scheduleCellService.getById(3L));
    }

    /**
     * Проверка того, что метод getAll выводит результат метода findAll репозитория.
     */
    @Test
    void getAllTest() {
        assertEquals(scheduleCellService.getAll(), scheduleCellRepository.findAll());
    }

    /**
     * Проверка того, что метод update возвращает обновлённую ячейку расписания с Id,
     * заданным в параметре.
     */
    @Test
    void updateTest() {
        ScheduleCell newScheduleCell = new ScheduleCell();

        doReturn(newScheduleCell).when(scheduleCellRepository).saveAndFlush(newScheduleCell);
        doReturn(true).when(scheduleCellRepository).existsById(3L);
        assertEquals(newScheduleCell, scheduleCellService.update(newScheduleCell, 3L));
        assertEquals(3L, scheduleCellService.update(newScheduleCell, 3L).getId());
    }

    /**
     * Проверка того, что метод delete возвращает true, если
     * EmptyResultDataAccessException не был брошен,
     * и возвращает false в обратном случае.
     */
    @Test
    void deleteTest() {
        assertTrue(scheduleCellService.delete(anyLong()));

        doThrow(EmptyResultDataAccessException.class).when(scheduleCellRepository).deleteById(anyLong());
        assertFalse(scheduleCellService.delete(anyLong()));
    }
}

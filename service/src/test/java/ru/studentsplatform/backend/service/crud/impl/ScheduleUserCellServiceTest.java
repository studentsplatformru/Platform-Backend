package ru.studentsplatform.backend.service.crud.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.domain.repository.ScheduleUserCellRepository;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleUserCell;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScheduleUserCellServiceTest {
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
}

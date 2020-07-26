package ru.studentsplatform.backend.service.crud.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.domain.repository.TaskRepository;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест каждого метода класса TaskServiceImpl
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 26.07.2020
 */
@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    TaskRepository repository;

    @InjectMocks
    TaskServiceImpl service;

    /**
     * Проверка того, что метод create возвращает созданную задачу.
     * Сымитировано поведение saveAndFlush.
     */
    @Test
    void createTest() {
        Task task = new Task();

        Mockito.when(repository.saveAndFlush(Mockito.any(Task.class))).thenReturn(task);
        assertEquals(task, service.create(task));
    }

    /**
     * Проверка того, что метод findById возвращает созданную задачу
     * и кидает NoSuchElementException при её отсутствии.
     */
    @Test
    void getByIdTest() {
        Task task = new Task();

        Mockito.when(repository.findById(2L)).thenReturn(java.util.Optional.of(task));

        assertEquals(repository.findById(2L).orElseThrow(), service.getById(2L));
        assertThrows(NoSuchElementException.class,() ->service.getById(3L));
    }

    /**
     * Проверка того, что метод getAll выводит результат метода findAll репозитория.
     */
    @Test
    void getAllTest() {
        assertEquals(service.getAll(),repository.findAll());
    }

    /**
     * Проверка того, что метод update возвращает обновлённую задачу с Id,
     * заданным в параметре.
     */
    @Test
    void updateTest() {
        Task newTask = new Task();

        Mockito.when(repository.saveAndFlush(newTask)).thenReturn(newTask);
        assertEquals(newTask, service.update(newTask,3L));
        assertEquals(3L, service.update(newTask, 3L).getId());
    }

    /**
     * Проверка того, что метод delete возвращает true, если
     * EmptyResultDataAccessException не был брошен,
     * и возвращает false в обратном случае.
     */
    @Test
    void deleteTest() {
        assertTrue(service.delete(Mockito.anyLong()));

        Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(Mockito.anyLong());
        assertFalse(service.delete(Mockito.anyLong()));
    }
}
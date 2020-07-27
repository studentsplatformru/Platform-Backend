package ru.studentsplatform.backend.service.crud.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.repository.TaskAttachmentRepository;
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.entities.model.utility.TaskAttachment;
import ru.studentsplatform.backend.service.exception.core.BusinessException;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskAttachmentServiceImplTest {

    @Mock
    TaskAttachmentRepository repository;

    @InjectMocks
    TaskAttachmentServiceImpl service;

    /**
     * Проверка того, что метод create возвращает созданное прикрепление к задаче.
     * Сымитировано поведение saveAndFlush.
     */
    @Test
    void createTest() {
        var taskAttachment = mock(TaskAttachment.class);

        doReturn(taskAttachment).when(repository).saveAndFlush(taskAttachment);

        var result = service.create(taskAttachment);
        assertEquals(taskAttachment, result);
    }

    /**
     * Проверка того, что метод findById возвращает созданное прикрепление к задаче
     * и кидает NoSuchElementException при его отсутствии.
     */
    @Test
    void getByIdTest() {
        TaskAttachment taskAttachment = new TaskAttachment();

        when(repository.findById(2L)).thenReturn(java.util.Optional.of(taskAttachment));

        assertEquals(repository.findById(2L).orElseThrow(), service.getById(2L));
        assertThrows(NoSuchElementException.class, () -> service.getById(3L));
    }

    /**
     * Проверка того, что метод getAll выводит результат метода findAll репозитория.
     */
    @Test
    void getAllTest() {
        assertEquals(service.getAll(), repository.findAll());
    }

    /**
     * Проверка того, что метод update возвращает обновлённое прикрепление к задаче с Id,
     * заданным в параметре.
     */
    @Test
    void updateTest() {
        TaskAttachment newTaskAttachment = new TaskAttachment();

        when(repository.saveAndFlush(newTaskAttachment)).thenReturn(newTaskAttachment);
        assertEquals(newTaskAttachment, service.update(newTaskAttachment, 3L));
        assertEquals(3L, service.update(newTaskAttachment, 3L).getId());
    }

    /**
     * Проверка того, что метод delete возвращает true, если
     * EmptyResultDataAccessException не был брошен,
     * и возвращает false в противном случае.
     */
    @Test
    void deleteTest() {
        assertTrue(service.delete(anyLong()));

        doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(anyLong());
        assertFalse(service.delete(anyLong()));
    }

    /**
     * Проверка того, что метод возвращает прикрепление к задаче с
     * данными, основанными на файле, переданном в параметре.
     */
    @Test
    void createByFile() {
        var file = mock(MultipartFile.class);

        var taskAttachment = mock(TaskAttachment.class);
        var task = mock(Task.class);

        doReturn(taskAttachment).when(repository).save(any(TaskAttachment.class));
        assertEquals(taskAttachment.getFileName(), service.createByFile(task, file).getFileName());
    }

    /**
     * Проверка того, что метод возвращает лист при указании корректного taskId
     */
    @Test
    void getByTaskId() {
        LinkedList taskAttachmentList = mock(LinkedList.class);
        doReturn(taskAttachmentList).when(repository).findByTaskId(3L);
        assertEquals(taskAttachmentList, service.getByTaskId(3L));
    }

    /**
     * Проверка того, что метод возвращает прикрепление, находящееся по порядковому номеру
     * в общем понимании (т.е. начиная с единицы)
     * В противном случае бросает бизнесс исключение
     */
    @Test
    void getByFileIndexTest(){
        TaskAttachment attachment = mock(TaskAttachment.class);
        LinkedList<TaskAttachment> linkedList = new LinkedList<>();
        linkedList.add(attachment);
        doReturn(linkedList).when(repository).findByTaskId(2L);
        assertEquals(linkedList.get(0), service.getByFileIndex(2L, 1));
        assertThrows(BusinessException.class,() -> service.getByFileIndex(2L, 3));
    }
}
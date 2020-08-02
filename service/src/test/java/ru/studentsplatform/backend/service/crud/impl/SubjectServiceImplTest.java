package ru.studentsplatform.backend.service.crud.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.domain.repository.SubjectRepository;
import ru.studentsplatform.backend.entities.model.university.Subject;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceImplTest {
    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectServiceImpl subjectService;

    /**
     * Проверка того, что метод create возвращает созданный предмет.
     * Сымитировано поведение saveAndFlush.
     */
    @Test
    void createTest() {
        var subject = mock(Subject.class);
        doReturn(subject).when(subjectRepository).save(subject);
        var result = subjectService.create(subject);
        assertEquals(subject, result);
    }

    /**
     * Проверка того, что метод findById возвращает созданный предмет
     * и кидает NoSuchElementException при её отсутствии.
     */
    @Test
    void getByIdTest() {
        Subject subject = new Subject();

        when(subjectRepository.findById(2L)).thenReturn(java.util.Optional.of(subject));

        assertEquals(subjectRepository.findById(2L).orElseThrow(), subjectService.getById(2L));
        assertThrows(BusinessException.class, () -> subjectService.getById(3L));
    }

    /**
     * Проверка того, что метод getAll выводит результат метода findAll репозитория.
     */
    @Test
    void getAllTest() {
        assertEquals(subjectService.getAll(), subjectRepository.findAll());
    }

    /**
     * Проверка того, что метод update возвращает обновлённый предмет с Id,
     * заданным в параметре.
     */
    @Test
    void updateTest() {
        Subject newSubject = new Subject();

        doReturn(newSubject).when(subjectRepository).saveAndFlush(newSubject);
        doReturn(true).when(subjectRepository).existsById(3L);
        assertEquals(newSubject, subjectService.update(newSubject, 3L));
        assertEquals(3L, subjectService.update(newSubject, 3L).getId());
    }

    /**
     * Проверка того, что метод delete возвращает true, если
     * EmptyResultDataAccessException не был брошен,
     * и возвращает false в обратном случае.
     */
    @Test
    void deleteTest() {
        assertTrue(subjectService.delete(anyLong()));

        doThrow(EmptyResultDataAccessException.class).when(subjectRepository).deleteById(anyLong());
        assertFalse(subjectService.delete(anyLong()));
    }
}

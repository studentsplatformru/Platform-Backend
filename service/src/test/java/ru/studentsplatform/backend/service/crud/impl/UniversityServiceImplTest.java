package ru.studentsplatform.backend.service.crud.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.domain.repository.UniversityRepository;
import ru.studentsplatform.backend.entities.model.university.University;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UniversityServiceImplTest {
    @Mock
    private UniversityRepository universityRepository;

    @InjectMocks
    private UniversityServiceImpl universityService;

    /**
     * Проверка того, что метод create возвращает созданный унииверситет.
     * Сымитировано поведение saveAndFlush.
     */
    @Test
    void createTest() {
        var university = mock(University.class);
        doReturn(university).when(universityRepository).save(university);
        var result = universityService.create(university);
        assertEquals(university, result);
    }

    /**
     * Проверка того, что метод findById возвращает созданный унииверситет
     * и кидает NoSuchElementException при её отсутствии.
     */
    @Test
    void getByIdTest() {
        University university = new University();

        when(universityRepository.findById(2L)).thenReturn(java.util.Optional.of(university));

        assertEquals(universityRepository.findById(2L).orElseThrow(), universityService.getById(2L));
        assertThrows(BusinessException.class, () -> universityService.getById(3L));
    }

    /**
     * Проверка того, что метод getAll выводит результат метода findAll репозитория.
     */
    @Test
    void getAllTest() {
        assertEquals(universityService.getAll(), universityRepository.findAll());
    }

    /**
     * Проверка того, что метод update возвращает обновлённый унииверситет с Id,
     * заданным в параметре.
     */
    @Test
    void updateTest() {
        University newUniversity = new University();

        doReturn(newUniversity).when(universityRepository).saveAndFlush(newUniversity);
        doReturn(true).when(universityRepository).existsById(3L);
        assertEquals(newUniversity, universityService.update(newUniversity, 3L));
        assertEquals(3L, universityService.update(newUniversity, 3L).getId());
    }

    /**
     * Проверка того, что метод delete возвращает true, если
     * EmptyResultDataAccessException не был брошен,
     * и возвращает false в обратном случае.
     */
    @Test
    void deleteTest() {
        assertTrue(universityService.delete(anyLong()));

        doThrow(EmptyResultDataAccessException.class).when(universityRepository).deleteById(anyLong());
        assertFalse(universityService.delete(anyLong()));
    }
}

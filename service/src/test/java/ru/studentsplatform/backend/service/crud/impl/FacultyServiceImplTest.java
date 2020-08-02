package ru.studentsplatform.backend.service.crud.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.domain.repository.FacultyRepository;
import ru.studentsplatform.backend.domain.repository.UniversityRepository;
import ru.studentsplatform.backend.entities.model.university.Faculty;

import ru.studentsplatform.backend.entities.model.university.University;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceImplTest {

    @Mock
    private FacultyRepository facultyRepository;

    @Mock
    private UniversityRepository universityRepository;


    @InjectMocks
    private FacultyServiceImpl facultyService;

    /**
     * Проверка того, что метод create возвращает созданный институт.
     * Сымитировано поведение save.
     */
    @Test
    void createTest() {
        var faculty = mock(Faculty.class);
        var university = mock(University.class);
        doReturn(university).when(faculty).getUniversity();
        doReturn(1L).when(university).getId();
        doReturn(true).when(universityRepository).existsById(1L);
        doReturn(faculty).when(facultyRepository).save(faculty);

        var result = facultyService.create(faculty);
        assertEquals(faculty, result);
        doReturn(2L).when(university).getId();
        assertThrows(BusinessException.class,() -> facultyService.create(faculty));
    }

    /**
     * Проверка того, что метод findById возвращает созданный институт
     * и кидает NoSuchElementException при её отсутствии.
     */
    @Test
    void getByIdTest() {
        Faculty faculty = new Faculty();

        when(facultyRepository.findById(2L)).thenReturn(java.util.Optional.of(faculty));

        assertEquals(facultyRepository.findById(2L).orElseThrow(), facultyService.getById(2L));
        assertThrows(BusinessException.class, () -> facultyService.getById(3L));
    }

    /**
     * Проверка того, что метод getAll выводит результат метода findAll репозитория.
     */
    @Test
    void getAllTest() {
        assertEquals(facultyService.getAll(), facultyRepository.findAll());
    }

    /**
     * Проверка того, что метод update возвращает обновлённый институт с Id,
     * заданным в параметре.
     */
    @Test
    void updateTest() {
        Faculty newFaculty = new Faculty();

        when(facultyRepository.save(newFaculty)).thenReturn(newFaculty);
        assertEquals(newFaculty, facultyService.update(newFaculty, 3L));
        assertEquals(3L, facultyService.update(newFaculty, 3L).getId());
    }

    /**
     * Проверка того, что метод delete возвращает true, если
     * EmptyResultDataAccessException не был брошен,
     * и возвращает false в обратном случае.
     */
    @Test
    void deleteTest() {
        assertTrue(facultyService.delete(anyLong()));

        doThrow(EmptyResultDataAccessException.class).when(facultyRepository).deleteById(anyLong());
        assertFalse(facultyService.delete(anyLong()));
    }

}

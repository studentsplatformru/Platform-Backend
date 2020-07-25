package ru.studentsplatform.backend.service.crud.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.entities.model.Faculty;
import ru.studentsplatform.backend.entities.model.University;
import ru.studentsplatform.backend.repository.FacultyRepository;
import ru.studentsplatform.backend.repository.UniversityRepository;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест всех методов класса FacultyServiceImpl
 *
 * @author Archie-Vian
 *
 */
@ExtendWith(MockitoExtension.class)
class FacultyServiceImplTest {

    @Mock
    FacultyRepository repository;

    @Mock
    UniversityRepository universityRepository;

    @InjectMocks
    FacultyServiceImpl service;

    /**
     * Проверка того, что метод create возвращает созданный факультет.
     * Сымитировано поведение saveAndFlush.
     */
    @Test
    void createTest(){
        Faculty faculty = new Faculty();
        University university = new University();
        university.setUniversityName("test");
        faculty.setUniversity(university);

        Mockito.when(repository.saveAndFlush(Mockito.any(Faculty.class))).thenReturn(faculty);
        assertEquals(faculty, service.create(faculty));
    }

    /**
     * Проверка того, что метод findById возвращает существующий факультет
     * и кидает NoSuchElementException при его отсутствии.
     */
    @Test
    void findByIdTest(){
        Faculty faculty = new Faculty();

        Mockito.when(repository.findById(2L)).thenReturn(java.util.Optional.of(faculty));

        assertEquals( repository.findById(2L).orElseThrow(), service.getById(2L));
        assertThrows(NoSuchElementException.class,() ->service.getById(3L));
    }

    /**
     * Проверка того, что метод getAll выводит результат метода findAll репозитория.
     */
    @Test
    void findAllTest(){
        assertEquals(service.getAll(),repository.findAll());
    }

    /**
     * Проверка того, что метод update возвращает обновлённый факльтет с Id,
     * заданным в параметре.
     */
    @Test
    void updateTest(){
        Faculty newFaculty = new Faculty();

        Mockito.when(repository.saveAndFlush(newFaculty)).thenReturn(newFaculty);
        assertEquals(newFaculty, service.update(newFaculty,3L));
        assertEquals(3L, service.update(newFaculty, 3L).getId());
    }

    /**
     * Проверка того, что метод delete возвращает true, если
     * EmptyResultDataAccessException не был брошен,
     * и возвращает false в обратном случае.
     */
    @Test
    void deleteTest(){
        assertTrue(service.delete(Mockito.anyLong()));

        Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(Mockito.anyLong());
        assertFalse(service.delete(Mockito.anyLong()));
    }



}
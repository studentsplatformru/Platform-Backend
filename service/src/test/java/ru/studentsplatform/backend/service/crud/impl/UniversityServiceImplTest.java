package ru.studentsplatform.backend.service.crud.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.entities.model.University;
import ru.studentsplatform.backend.repository.UniversityRepository;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест всех методов класса UniversityServiceImpl
 *
 * @author Archie-Vian
 *
 */
@ExtendWith(MockitoExtension.class)
class UniversityServiceImplTest {

    @Mock
    UniversityRepository repository;

    @InjectMocks
    UniversityServiceImpl service;

    /**
     * Проверка того, что метод create возвращает созданного университета.
     * Сымитировано поведение saveAndFlush.
     */
    @Test
    void createTest(){
        University university = new University();

        Mockito.when(repository.saveAndFlush(Mockito.any(University.class))).thenReturn(university);
        assertEquals(university, service.create(university));
    }

    /**
     * Проверка того, что метод findById возвращает существующего университета
     * и кидает NoSuchElementException при его отсутствии.
     */
    @Test
    void findByIdTest(){
        University university = new University();

        Mockito.when(repository.findById(2L)).thenReturn(java.util.Optional.of(university));

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
     * Проверка того, что метод update возвращает обновлённого университета с Id,
     * заданным в параметре.
     */
    @Test
    void updateTest(){
        University newUniversity = new University();

        Mockito.when(repository.saveAndFlush(newUniversity)).thenReturn(newUniversity);
        assertEquals(newUniversity, service.update(newUniversity,3L));
        assertEquals(3L, service.update(newUniversity, 3L).getId());
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
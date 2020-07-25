package ru.studentsplatform.backend.service.crud.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.entities.model.Team;
import ru.studentsplatform.backend.repository.TeamRepository;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест всех методов класса TeamServiceImpl
 *
 * @author Archie-Vian
 *
 */
@ExtendWith(MockitoExtension.class)
class TeamServiceImplTest {

    @Mock
    TeamRepository repository;

    @InjectMocks
    TeamServiceImpl service;

    /**
     * Проверка того, что метод create возвращает созданную группу.
     * Сымитировано поведение saveAndFlush.
     */
    @Test
    void createTest(){
        Team team = new Team();

        Mockito.when(repository.saveAndFlush(Mockito.any(Team.class))).thenReturn(team);
        assertEquals(team, service.create(team));
    }

    /**
     * Проверка того, что метод findById возвращает созданную группу
     * и кидает NoSuchElementException при её отсутствии.
     */
    @Test
    void findByIdTest(){
        Team team = new Team();

        Mockito.when(repository.findById(2L)).thenReturn(java.util.Optional.of(team));

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
     * Проверка того, что метод update возвращает обновлённую группу с Id,
     * заданным в параметре.
     */
    @Test
    void updateTest(){
        Team newTeam = new Team();

        Mockito.when(repository.saveAndFlush(newTeam)).thenReturn(newTeam);
        assertEquals(newTeam, service.update(newTeam,3L));
        assertEquals(3L, service.update(newTeam, 3L).getId());
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
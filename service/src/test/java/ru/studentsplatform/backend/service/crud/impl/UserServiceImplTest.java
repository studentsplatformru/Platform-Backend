package ru.studentsplatform.backend.service.crud.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.entities.model.User;
import ru.studentsplatform.backend.repository.UserRepository;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест всех методов класса UserServiceImpl
 *
 * @author Archie-Vian
 *
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    UserServiceImpl service;

    /**
     * Проверка того, что метод create возвращает созданного пользователя.
     * Сымитировано поведение saveAndFlush.
     */
    @Test
    void createTest(){
        User user = new User();

        Mockito.when(repository.saveAndFlush(Mockito.any(User.class))).thenReturn(user);
        assertEquals(user, service.create(user));
    }

    /**
     * Проверка того, что метод findById возвращает существующего пользователя
     * и кидает NoSuchElementException при его отсутствии.
     */
    @Test
    void findByIdTest(){
        User user = new User();

        Mockito.when(repository.findById(2L)).thenReturn(java.util.Optional.of(user));

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
     * Проверка того, что метод update возвращает обновлённого пользователя с Id,
     * заданным в параметре.
     */
    @Test
    void updateTest(){
        User newUser = new User();

        Mockito.when(repository.saveAndFlush(newUser)).thenReturn(newUser);
        assertEquals(newUser, service.update(newUser,3L));
        assertEquals(3L, service.update(newUser, 3L).getId());
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
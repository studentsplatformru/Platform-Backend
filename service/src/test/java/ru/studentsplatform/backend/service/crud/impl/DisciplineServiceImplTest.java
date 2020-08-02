package ru.studentsplatform.backend.service.crud.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.domain.repository.DisciplineRepository;
import ru.studentsplatform.backend.domain.repository.SubjectRepository;
import ru.studentsplatform.backend.domain.repository.UserRepository;
import ru.studentsplatform.backend.entities.model.university.Discipline;
import ru.studentsplatform.backend.entities.model.university.Subject;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DisciplineServiceImplTest {

    @Mock
    private DisciplineRepository disciplineRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    SubjectRepository subjectRepository;


    @InjectMocks
    private DisciplineServiceImpl disciplineService;

    /**
     * Проверка того, что метод create возвращает созданную дисциплину.
     * Сымитировано поведение save.
     */
    @Test
    void createTest() {
        var discipline = mock(Discipline.class);
        var user = mock(User.class);
        var subject = mock(Subject.class);
        doReturn(user).when(discipline).getUser();
        doReturn(1L).when(user).getId();
        doReturn(true).when(userRepository).existsById(1L);

        doReturn(subject).when(discipline).getSubject();
        doReturn(1L).when(subject).getId();
        doReturn(true).when(subjectRepository).existsById(1L);

        doReturn(discipline).when(disciplineRepository).save(discipline);

        var result = disciplineService.create(discipline);
        assertEquals(discipline, result);
        doReturn(2L).when(user).getId();
        assertThrows(BusinessException.class,() -> disciplineService.create(discipline));
    }

    /**
     * Проверка того, что метод findById возвращает созданную дисциплину
     * и кидает NoSuchElementException при её отсутствии.
     */
    @Test
    void getByIdTest() {
        Discipline discipline = new Discipline();

        when(disciplineRepository.findById(2L)).thenReturn(java.util.Optional.of(discipline));

        assertEquals(disciplineRepository.findById(2L).orElseThrow(), disciplineService.getById(2L));
        assertThrows(BusinessException.class, () -> disciplineService.getById(3L));
    }

    /**
     * Проверка того, что метод getAll выводит результат метода findAll репозитория.
     */
    @Test
    void getAllTest() {
        assertEquals(disciplineService.getAll(), disciplineRepository.findAll());
    }

    /**
     * Проверка того, что метод update возвращает обновлённую дисциплину с Id,
     * заданным в параметре.
     */
    @Test
    void updateTest() {
        Discipline newDiscipline = new Discipline();

        doReturn(newDiscipline).when(disciplineRepository).save(newDiscipline);
        assertEquals(newDiscipline, disciplineService.update(newDiscipline, 3L));
        assertEquals(3L, disciplineService.update(newDiscipline, 3L).getId());
    }

    /**
     * Проверка того, что метод delete возвращает true, если
     * EmptyResultDataAccessException не был брошен,
     * и возвращает false в обратном случае.
     */
    @Test
    void deleteTest() {
        assertTrue(disciplineService.delete(anyLong()));

        doThrow(EmptyResultDataAccessException.class).when(disciplineRepository).deleteById(anyLong());
        assertFalse(disciplineService.delete(anyLong()));
    }

}

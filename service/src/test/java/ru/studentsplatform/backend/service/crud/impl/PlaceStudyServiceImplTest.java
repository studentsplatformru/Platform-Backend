package ru.studentsplatform.backend.service.crud.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.domain.repository.*;
import ru.studentsplatform.backend.entities.model.university.*;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlaceStudyServiceImplTest {
    @Mock
    private PlaceStudyRepository placeStudyRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    FacultyRepository facultyRepository;

    @Mock
    private UniversityRepository universityRepository;

    @Mock
    DepartmentRepository departmentRepository;

    @Mock
    private DirectionRepository directionRepository;

    @Mock
    TeamRepository teamRepository;


    @InjectMocks
    private PlaceStudyServiceImpl placeStudyService;

    /**
     * Проверка того, что метод create возвращает созданное место заниятий.
     * Сымитировано поведение save.
     */
    @Test
    void createTest() {
        var placeStudy = mock(PlaceStudy.class);
        var user = mock(User.class);
        var faculty = mock(Faculty.class);
        var university = mock(University.class);
        var department = mock(Department.class);
        var direction = mock(Direction.class);
        var team = mock(Team.class);
        doReturn(user).when(placeStudy).getUser();
        doReturn(1L).when(user).getId();
        doReturn(true).when(userRepository).existsById(1L);

        doReturn(faculty).when(placeStudy).getFaculty();
        doReturn(1L).when(faculty).getId();
        doReturn(true).when(facultyRepository).existsById(1L);

        doReturn(university).when(placeStudy).getUniversity();
        doReturn(1L).when(university).getId();
        doReturn(true).when(universityRepository).existsById(1L);

        doReturn(department).when(placeStudy).getDepartment();
        doReturn(1L).when(department).getId();
        doReturn(true).when(departmentRepository).existsById(1L);

        doReturn(direction).when(placeStudy).getDirection();
        doReturn(1L).when(direction).getId();
        doReturn(true).when(directionRepository).existsById(1L);

        doReturn(team).when(placeStudy).getTeam();
        doReturn(1L).when(team).getId();
        doReturn(true).when(teamRepository).existsById(1L);

        doReturn(placeStudy).when(placeStudyRepository).saveAndFlush(placeStudy);

        var result = placeStudyService.create(placeStudy);
        assertEquals(placeStudy, result);
        doReturn(2L).when(user).getId();
        assertThrows(BusinessException.class,() -> placeStudyService.create(placeStudy));
    }

    /**
     * Проверка того, что метод findById возвращает созданное место заниятий
     * и кидает NoSuchElementException при её отсутствии.
     */
    @Test
    void getByIdTest() {
        PlaceStudy placeStudy = new PlaceStudy();

        when(placeStudyRepository.findById(2L)).thenReturn(java.util.Optional.of(placeStudy));

        assertEquals(placeStudyRepository.findById(2L).orElseThrow(), placeStudyService.getById(2L));
        assertThrows(BusinessException.class, () -> placeStudyService.getById(3L));
    }

    /**
     * Проверка того, что метод getAll выводит результат метода findAll репозитория.
     */
    @Test
    void getAllTest() {
        assertEquals(placeStudyService.getAll(), placeStudyRepository.findAll());
    }

    /**
     * Проверка того, что метод update возвращает обновлённое место заниятий с Id,
     * заданным в параметре.
     */
    @Test
    void updateTest() {
        PlaceStudy newPlaceStudy = new PlaceStudy();

        doReturn(newPlaceStudy).when(placeStudyRepository).save(newPlaceStudy);
        assertEquals(newPlaceStudy, placeStudyService.update(newPlaceStudy, 3L));
        assertEquals(3L, placeStudyService.update(newPlaceStudy, 3L).getId());
    }

    /**
     * Проверка того, что метод delete возвращает true, если
     * EmptyResultDataAccessException не был брошен,
     * и возвращает false в обратном случае.
     */
    @Test
    void deleteTest() {
        assertTrue(placeStudyService.delete(anyLong()));

        doThrow(EmptyResultDataAccessException.class).when(placeStudyRepository).deleteById(anyLong());
        assertFalse(placeStudyService.delete(anyLong()));
    }
}

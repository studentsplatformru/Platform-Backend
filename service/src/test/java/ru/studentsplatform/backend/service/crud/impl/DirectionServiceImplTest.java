package ru.studentsplatform.backend.service.crud.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.domain.repository.DirectionRepository;
import ru.studentsplatform.backend.domain.repository.FacultyRepository;
import ru.studentsplatform.backend.entities.model.university.Direction;
import ru.studentsplatform.backend.entities.model.university.Faculty;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DirectionServiceImplTest {

	@Mock
	private DirectionRepository directionRepository;

	@Mock
	private FacultyRepository facultyRepository;


	@InjectMocks
	private DirectionServiceImpl directionService;

	/**
	 * Проверка того, что метод create возвращает созданное направление.
	 * Сымитировано поведение save.
	 */
	@Test
	void createTest() {
		var direction = mock(Direction.class);
		var faculty = mock(Faculty.class);
		doReturn(faculty).when(direction).getFaculty();
		doReturn(1L).when(faculty).getId();
		doReturn(true).when(facultyRepository).existsById(1L);
		doReturn(direction).when(directionRepository).save(direction);

		var result = directionService.create(direction);
		assertEquals(direction, result);
		doReturn(2L).when(faculty).getId();
		assertThrows(BusinessException.class, () -> directionService.create(direction));
	}

	/**
	 * Проверка того, что метод findById возвращает созданное направление
	 * и кидает NoSuchElementException при её отсутствии.
	 */
	@Test
	void getByIdTest() {
		Direction direction = new Direction();

		when(directionRepository.findById(2L)).thenReturn(java.util.Optional.of(direction));

		assertEquals(directionRepository.findById(2L).orElseThrow(), directionService.getById(2L));
		assertThrows(BusinessException.class, () -> directionService.getById(3L));
	}

	/**
	 * Проверка того, что метод getAll выводит результат метода findAll репозитория.
	 */
	@Test
	void getAllTest() {
		assertEquals(directionService.getAll(), directionRepository.findAll());
	}

	/**
	 * Проверка того, что метод update возвращает обновлённое направление с Id,
	 * заданным в параметре.
	 */
	@Test
	void updateTest() {
		Direction newDirection = new Direction();

		when(directionRepository.saveAndFlush(newDirection)).thenReturn(newDirection);
		assertEquals(newDirection, directionService.update(newDirection, 3L));
		assertEquals(3L, directionService.update(newDirection, 3L).getId());
	}

	/**
	 * Проверка того, что метод delete возвращает true, если
	 * EmptyResultDataAccessException не был брошен,
	 * и возвращает false в обратном случае.
	 */
	@Test
	void deleteTest() {
		assertTrue(directionService.delete(anyLong()));

		doThrow(EmptyResultDataAccessException.class).when(directionRepository).deleteById(anyLong());
		assertFalse(directionService.delete(anyLong()));
	}

}

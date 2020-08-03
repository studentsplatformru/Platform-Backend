package ru.studentsplatform.backend.service.crud.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.domain.repository.TeamRepository;
import ru.studentsplatform.backend.entities.model.university.Team;
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
public class TeamServiceImplTest {
	@Mock
	private TeamRepository teamRepository;

	@InjectMocks
	private TeamServiceImpl teamService;

	/**
	 * Проверка того, что метод create возвращает созданную группу.
	 * Сымитировано поведение saveAndFlush.
	 */
	@Test
	void createTest() {
		var team = mock(Team.class);
		doReturn(team).when(teamRepository).save(team);
		var result = teamService.create(team);
		assertEquals(team, result);
	}

	/**
	 * Проверка того, что метод findById возвращает созданную группу
	 * и кидает NoSuchElementException при её отсутствии.
	 */
	@Test
	void getByIdTest() {
		Team team = new Team();

		when(teamRepository.findById(2L)).thenReturn(java.util.Optional.of(team));

		assertEquals(teamRepository.findById(2L).orElseThrow(), teamService.getById(2L));
		assertThrows(BusinessException.class, () -> teamService.getById(3L));
	}

	/**
	 * Проверка того, что метод getAll выводит результат метода findAll репозитория.
	 */
	@Test
	void getAllTest() {
		assertEquals(teamService.getAll(), teamRepository.findAll());
	}

	/**
	 * Проверка того, что метод update возвращает обновлённую группу с Id,
	 * заданным в параметре.
	 */
	@Test
	void updateTest() {
		Team newTeam = new Team();

		doReturn(newTeam).when(teamRepository).saveAndFlush(newTeam);
		doReturn(true).when(teamRepository).existsById(3L);
		assertEquals(newTeam, teamService.update(newTeam, 3L));
		assertEquals(3L, teamService.update(newTeam, 3L).getId());
	}

	/**
	 * Проверка того, что метод delete возвращает true, если
	 * EmptyResultDataAccessException не был брошен,
	 * и возвращает false в обратном случае.
	 */
	@Test
	void deleteTest() {
		assertTrue(teamService.delete(anyLong()));

		doThrow(EmptyResultDataAccessException.class).when(teamRepository).deleteById(anyLong());
		assertFalse(teamService.delete(anyLong()));
	}
}

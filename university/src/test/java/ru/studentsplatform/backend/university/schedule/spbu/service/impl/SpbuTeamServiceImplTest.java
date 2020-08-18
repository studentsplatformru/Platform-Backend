package ru.studentsplatform.backend.university.schedule.spbu.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.domain.repository.spbu.SpbuTeamRepository;
import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * Тесты методов класса SpbuTeamServiceImpl.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 10.08.2020
 */
@ExtendWith(MockitoExtension.class)
class SpbuTeamServiceImplTest {

	@Mock
	private SpbuTeamRepository repository;

	@InjectMocks
	private SpbuTeamServiceImpl service;

	/**
	 * Проверка того, что метод create возвращает созранённый объект.
	 */
	@Test
	void createTest() {
		var team = mock(SpbuTeam.class);
		doReturn(team).when(repository).save(team);
		assertEquals(team, service.create(team));
	}

	/**
	 * Проверка того, что метод возвращает наденный по имени объект SpbuTeam.
	 */
	@Test
	void getByNameTest() {
		var team = new SpbuTeam();
		team.setName("test");
		doReturn(team).when(repository).findByName(team.getName());
		assertEquals(team.getName(), service.getByName(team.getName()).getName());

	}

}
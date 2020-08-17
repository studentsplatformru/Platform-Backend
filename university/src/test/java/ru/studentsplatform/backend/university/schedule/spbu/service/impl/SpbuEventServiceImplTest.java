package ru.studentsplatform.backend.university.schedule.spbu.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.domain.repository.spbu.SpbuEventRepository;
import ru.studentsplatform.backend.entities.model.spbu.SpbuEvent;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class SpbuEventServiceImplTest {

	@Mock
	SpbuEventRepository repository;

	@InjectMocks
	SpbuEventServiceImpl service;

	private static SpbuEvent spbuEvent = mock(SpbuEvent.class);
	private static List<SpbuEvent> events = new LinkedList();

	@BeforeAll
	static void init() {
		events.add(spbuEvent);
	}

	/**
	 * Тест того, что метод create ывозвращает созданный объект.
	 */
	@Test
	void CreateTest() {
		doReturn(spbuEvent).when(repository).save(spbuEvent);
		assertEquals(service.create(spbuEvent), spbuEvent);
	}

	/**
	 * Тест того, что метод возвращает группу, найденную в репозитории по имени.
	 */
	@Test
	void getByGroupNameTest() {
		doReturn(events).when(repository).findByTeamName(anyString());
		assertEquals(service.getByGroupName(anyString()), events);
	}
}

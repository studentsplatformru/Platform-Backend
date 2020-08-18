package ru.studentsplatform.backend.university.schedule.spbu.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuEventDTO;
import ru.studentsplatform.backend.entities.model.spbu.SpbuEvent;
import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Тесты методов класса SpbuEventMapper.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 17.08.2020
 */
public class SpbuEventMapperTest {

	private SpbuEventMapperImpl mapper = new SpbuEventMapperImpl();

	private SpbuEventDTO dto;
	private SpbuEvent entity;


	@BeforeEach
	void Preparation() {
		dto = new SpbuEventDTO();
		dto.setId(1L);
		dto.setSubject("test");
		dto.setSpbuTeamName("TEST");

		SpbuTeam team = new SpbuTeam();
		entity = new SpbuEvent();
		entity.setId(1L);
		entity.setSubject("test");
		entity.setTeam(team);
		entity.getTeam().setName("TEST");
	}

	/**
	 * Проверка возможности создать сущность на основе DTO.
	 */
	@Test
	void SpbuEventDTOToEventTest() {
		SpbuEvent entity = mapper.spbuEventDTOToSpbuEvent(dto);
		assertEquals(dto.getId(), entity.getId());
		assertEquals(dto.getSubject(), entity.getSubject());
		assertEquals(dto.getSpbuTeamName(), entity.getTeam().getName());
	}

	/**
	 * Проверка возможности создать DTO на основе сущности.
	 */
	@Test
	void SpbuEventToEventDTOTest() {

		SpbuEventDTO dto = mapper.spbuEventToSpbuEventDTO(entity);
		assertEquals(dto.getId(), entity.getId());
		assertEquals(dto.getSubject(), entity.getSubject());
		assertEquals(dto.getSpbuTeamName(), entity.getTeam().getName());
	}

	/**
	 * Проверка возможности создать список DTO на основе списка сущностей.
	 */
	@Test
	void ListSpbuEventDTOToEvent() {
		List<SpbuEventDTO> spbuEventDTOS = new LinkedList<>();
		spbuEventDTOS.add(dto);
		spbuEventDTOS.add(dto);
		List<SpbuEvent> spbuEvents = mapper.listSpbuEventDTOToSpbuEvent(spbuEventDTOS);
		assertEquals(1L, spbuEvents.get(0).getId());
		assertEquals(1L, spbuEvents.get(1).getId());
		assertEquals("test", spbuEvents.get(0).getSubject());
		assertEquals("test", spbuEvents.get(1).getSubject());
		assertEquals("TEST", spbuEvents.get(0).getTeam().getName());
		assertEquals("TEST", spbuEvents.get(1).getTeam().getName());
	}

	/**
	 * Проверка возможности создать список сущностей на основе списка DTO.
	 */
	@Test
	void ListSpbuEventToEventDTO() {
		List<SpbuEvent> spbuEvents = new LinkedList<>();
		spbuEvents.add(entity);
		spbuEvents.add(entity);
		List<SpbuEventDTO> spbuEventDTOs = mapper.listSpbuEventToSpbuEventDTO(spbuEvents);
		assertEquals(1L, spbuEvents.get(0).getId());
		assertEquals(1L, spbuEvents.get(1).getId());
		assertEquals("test", spbuEvents.get(0).getSubject());
		assertEquals("test", spbuEvents.get(1).getSubject());
		assertEquals("TEST", spbuEvents.get(0).getTeam().getName());
		assertEquals("TEST", spbuEvents.get(1).getTeam().getName());
	}
}

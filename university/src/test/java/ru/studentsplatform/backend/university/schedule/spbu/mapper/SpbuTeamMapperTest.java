package ru.studentsplatform.backend.university.schedule.spbu.mapper;

import org.junit.jupiter.api.Test;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuTeamDTO;
import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpbuTeamMapperTest {

	private SpbuTeamMapperImpl mapper = new SpbuTeamMapperImpl();

	/**
	 * Проверка возможности создать сущность на основе DTO.
	 */
	@Test
	void SpbuTeamDTOToTeamTest() {
		SpbuTeamDTO dto = new SpbuTeamDTO();
		dto.setId(1L);
		dto.setName("test");
		dto.setAlias("TEST");
		SpbuTeam entity = mapper.spbuTeamDTOToSpbuTeam(dto);
		assertEquals(dto.getId(), entity.getId());
		assertEquals(dto.getName(), entity.getName());
		assertEquals(dto.getAlias(), entity.getAlias());
	}

	/**
	 * Проверка возможности создать DTO на основе сущности.
	 */
	@Test
	void SpbuTeamToTeamDTOTest() {
		SpbuTeam entity = new SpbuTeam();
		entity.setId(1L);
		entity.setName("test");
		entity.setAlias("TEST");
		SpbuTeamDTO dto = mapper.spbuTeamToTeamDTO(entity);
		assertEquals(dto.getId(), entity.getId());
		assertEquals(dto.getName(), entity.getName());
		assertEquals(dto.getAlias(), entity.getAlias());
	}

	/**
	 * Проверка возможности создать список DTO на основе списка сущностей.
	 */
	@Test
	void ListSpbuTeamDTOToTeam() {
		List<SpbuTeamDTO> spbuTeamDTOS = new LinkedList<>();
		SpbuTeamDTO dto = new SpbuTeamDTO();
		dto.setId(5L);
		dto.setName("test");
		dto.setAlias("TEST");
		spbuTeamDTOS.add(dto);
		spbuTeamDTOS.add(dto);
		List<SpbuTeam> spbuTeams = mapper.listSpbuTeamDTOToSpbuTeam(spbuTeamDTOS);
		assertEquals(5L, spbuTeams.get(0).getId());
		assertEquals(5L, spbuTeams.get(1).getId());
		assertEquals("test", spbuTeams.get(0).getName());
		assertEquals("test", spbuTeams.get(1).getName());
		assertEquals("TEST", spbuTeams.get(0).getAlias());
		assertEquals("TEST", spbuTeams.get(1).getAlias());
	}

	/**
	 * Проверка возможности создать список сущностей на основе списка DTO.
	 */
	@Test
	void ListSpbuTeamToTeamDTO() {
		List<SpbuTeam> spbuTeams = new LinkedList<>();
		SpbuTeam entity = new SpbuTeam();
		entity.setId(5L);
		entity.setName("test");
		entity.setAlias("TEST");
		spbuTeams.add(entity);
		spbuTeams.add(entity);
		List<SpbuTeamDTO> spbuTeamDTOs = mapper.listSpbuTeamToSpbuTeamDTO(spbuTeams);
		assertEquals(5L, spbuTeams.get(0).getId());
		assertEquals(5L, spbuTeams.get(1).getId());
		assertEquals("test", spbuTeams.get(0).getName());
		assertEquals("test", spbuTeams.get(1).getName());
		assertEquals("TEST", spbuTeams.get(0).getAlias());
		assertEquals("TEST", spbuTeams.get(1).getAlias());
	}

}

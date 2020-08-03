package ru.studentsplatform.backend.endpoint.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.domain.dto.schedule.ScheduleCellDTO;
import ru.studentsplatform.backend.entities.model.enums.ClassTypeEnum;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleCell;
import ru.studentsplatform.backend.entities.model.university.Subject;
import ru.studentsplatform.backend.entities.model.university.Team;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ScheduleCellMapperTest {

	ScheduleCellMapper mapper = new ScheduleCellMapperImpl();

	/**
	 * Проверка того, что маппер может создать DTO на основе сущности
	 */
	@Test
	void scheduleCellToScheduleCellDTOTest() {
		ScheduleCell entity = new ScheduleCell();
		Team team = new Team();
		Subject subject = new Subject();
		subject.setId(1L);
		team.setId(1L);
		entity.setStartClass(OffsetDateTime.of(LocalDateTime.
						of(2017, 5, 12, 5, 45, 5),
				ZoneOffset.ofHoursMinutes(6, 0)));
		entity.setEndClass(OffsetDateTime.of(LocalDateTime.
						of(2017, 5, 12, 6, 45, 5),
				ZoneOffset.ofHoursMinutes(6, 0)));
		entity.setTeam(team);
		entity.setSubject(subject);
		entity.setType(ClassTypeEnum.LECTURE);
		ScheduleCellDTO dto = mapper.scheduleCellToScheduleCellDTO(entity);
		assertEquals(dto.getStartClass(), entity.getStartClass());
		assertEquals(dto.getEndClass(), entity.getEndClass());
		assertEquals(dto.getTeamId(), entity.getTeam().getId());
		assertEquals(dto.getSubjectId(), entity.getSubject().getId());
		assertEquals(dto.getType(), entity.getType().toString());
	}

	/**
	 * Проверка того, что маппер способен создать сущность на основе DTO
	 */
	@Test
	void scheduleCellDTOToScheduleCell() {
		ScheduleCellDTO dto = new ScheduleCellDTO();
		dto.setStartClass(OffsetDateTime.of(LocalDateTime.
						of(2017, 5, 12, 5, 45, 5),
				ZoneOffset.ofHoursMinutes(6, 0)));
		dto.setEndClass(OffsetDateTime.of(LocalDateTime.
						of(2017, 5, 12, 6, 45, 5),
				ZoneOffset.ofHoursMinutes(6, 0)));
		dto.setTeamId(1L);
		dto.setSubjectId(2L);
		dto.setType("LECTURE");
		ScheduleCell entity = mapper.scheduleCellDTOToScheduleCell(dto);
		assertEquals(dto.getStartClass(), entity.getStartClass());
		assertEquals(dto.getEndClass(), entity.getEndClass());
		assertEquals(dto.getTeamId(), entity.getTeam().getId());
		assertEquals(dto.getSubjectId(), entity.getSubject().getId());
		assertEquals(dto.getType(), entity.getType().toString());
	}

	/**
	 * Проверка того, что маппер способен создать лист DTO на основе листа сущностей
	 */
	@Test
	void listScheduleCellToScheduleCellDTOTest() {
		List<ScheduleCell> scheduleCellList = new LinkedList<>();
		ScheduleCell testScheduleCell = new ScheduleCell();
		testScheduleCell.setId(3L);
		scheduleCellList.add(testScheduleCell);
		scheduleCellList.add(testScheduleCell);
		List<ScheduleCellDTO> scheduleCellDTOS = mapper.listScheduleCellToScheduleCellDTO(scheduleCellList);
		assertEquals(3L, scheduleCellDTOS.get(0).getId());
		assertEquals(3L, scheduleCellDTOS.get(1).getId());
	}

	/**
	 * Проверка того, что маппер способен создать лист сущностей на основе листа DTO
	 */
	@Test
	void listScheduleCellDTOtoScheduleCellTest() {
		List<ScheduleCellDTO> scheduleCellDTOS = new LinkedList<>();
		ScheduleCellDTO testScheduleCellDTO = new ScheduleCellDTO();
		testScheduleCellDTO.setId(5L);
		scheduleCellDTOS.add(testScheduleCellDTO);
		scheduleCellDTOS.add(testScheduleCellDTO);
		List<ScheduleCell> scheduleCells = mapper.listScheduleCellDTOToScheduleCell(scheduleCellDTOS);
		assertEquals(5L, scheduleCells.get(0).getId());
		assertEquals(5L, scheduleCells.get(1).getId());
	}

}

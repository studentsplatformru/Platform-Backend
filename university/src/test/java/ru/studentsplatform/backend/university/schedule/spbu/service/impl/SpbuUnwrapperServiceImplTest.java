package ru.studentsplatform.backend.university.schedule.spbu.service.impl;

import org.junit.jupiter.api.Test;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuEventDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuProgramCombinationDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuProgramLevelDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuScheduleDayDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuStudyProgramDTO;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpbuUnwrapperServiceImplTest {

	SpbuUnwrapServiceImpl unwrapperService = new SpbuUnwrapServiceImpl();

	/**
	 * Проверка способности метода разворачивать класс-оболочку SpbuProgramLevelDTO.
	 */
	@Test
	void studyProgramUnwrapTest() {
		var studPrograms = new LinkedList<SpbuStudyProgramDTO>();
		studPrograms.add(new SpbuStudyProgramDTO());
		studPrograms.get(0).setProgramId(111L);

		var combinations = new LinkedList<SpbuProgramCombinationDTO>();
		combinations.add(new SpbuProgramCombinationDTO());
		combinations.get(0).setStudyPrograms(studPrograms);

		var levels = new LinkedList<SpbuProgramLevelDTO>();
		levels.add(new SpbuProgramLevelDTO());
		levels.get(0).setProgramCombinations(combinations);

		assertEquals(unwrapperService.studyProgramUnwrap(levels).get(0).getProgramId(), studPrograms.get(0).getProgramId());

	}

	/**
	 * Проверка способности метода разворачивать класс-оболочку SpbuScheduleDay.
	 */
	@Test
	void eventUnwrapTest() {
		var events = new LinkedList<SpbuEventDTO>();
		events.add(new SpbuEventDTO());
		events.get(0).setSubject("test");

		var days = new LinkedList<SpbuScheduleDayDTO>();
		days.add(new SpbuScheduleDayDTO());
		days.get(0).setEvents(events);

		assertEquals(unwrapperService.eventUnwrap(days).get(0).getSubject(), events.get(0).getSubject());
	}
}

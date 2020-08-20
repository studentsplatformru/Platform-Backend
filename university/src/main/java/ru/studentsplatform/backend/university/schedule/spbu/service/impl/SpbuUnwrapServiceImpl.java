package ru.studentsplatform.backend.university.schedule.spbu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuEventTransfer;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuProgramCombinationDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuProgramLevelDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuScheduleDayDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuStudyProgramDTO;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuUnwrapService;

import java.util.LinkedList;
import java.util.List;

/**
 * Имплементация сервиса для разворачиваня классов обёрток данных СПБГУ.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 16.08.2020
 */
@Slf4j
@Service
@Profiled
public class SpbuUnwrapServiceImpl implements SpbuUnwrapService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LinkedList<SpbuStudyProgramDTO> studyProgramUnwrap(List<SpbuProgramLevelDTO> programLevels) {
		var programCombinations = new LinkedList<SpbuProgramCombinationDTO>();
		var studyPrograms = new LinkedList<SpbuStudyProgramDTO>();

		programLevels.forEach(dto -> programCombinations.addAll(dto.getProgramCombinations()));
		programCombinations.forEach(dto -> studyPrograms.addAll(dto.getStudyPrograms()));

		return studyPrograms;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SpbuEventTransfer> eventUnwrap(List<SpbuScheduleDayDTO> days) {
		log.info("Unwrapping {} objects...", days.size());

		var events = new LinkedList<SpbuEventTransfer>();
		days.forEach(day -> events.addAll(day.getEvents()));

		return events;
	}

}

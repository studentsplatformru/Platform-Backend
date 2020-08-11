package ru.studentsplatform.backend.university.schedule.spbu.service.impl;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuEventDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuProgramCombinationDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuProgramLevelDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuScheduleDayDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuStudyProgramDTO;
import ru.studentsplatform.backend.domain.repository.spbu.SpbuTeamRepository;
import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuService;

import java.util.LinkedList;
import java.util.List;

@Service
@Profiled
public class SpbuServiceImpl implements SpbuService {

	private final SpbuTeamRepository repository;

	public SpbuServiceImpl(SpbuTeamRepository repository) {
		this.repository = repository;
	}

	/**
	 *
	 * {@inheritDoc}
	 */
	@Override
	public LinkedList<SpbuStudyProgramDTO> studyProgramUnwrap(List<SpbuProgramLevelDTO> programLevels) {
		var programCombinations = new LinkedList<SpbuProgramCombinationDTO>();

		var studyPrograms = new LinkedList<SpbuStudyProgramDTO>();

		for (SpbuProgramLevelDTO dto: programLevels) {
			programCombinations.addAll(dto.getProgramCombinations());
		}
		programLevels.forEach(dto -> programCombinations.addAll(dto.getProgramCombinations()));
		programCombinations.forEach(dto -> studyPrograms.addAll(dto.getStudyPrograms()));
		return studyPrograms;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SpbuEventDTO> eventUnwrap(List<SpbuScheduleDayDTO> days) {
		var events = new LinkedList<SpbuEventDTO>();
		days.forEach(day -> events.addAll(day.getEvents()));
		return events;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SpbuTeam create(SpbuTeam team) {
		return repository.save(team);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SpbuTeam getByName(String name) {
		return repository.findByName(name);
	}
}

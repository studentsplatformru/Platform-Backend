package ru.studentsplatform.backend.university.schedule.spbu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuEventDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuProgramCombinationDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuProgramLevelDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuScheduleDayDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuStudyProgramDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuTeamDTO;
import ru.studentsplatform.backend.domain.repository.spbu.SpbuTeamRepository;
import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;
import ru.studentsplatform.backend.service.proxy.SpbuProxy;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;
import ru.studentsplatform.backend.university.schedule.spbu.mapper.SpbuTeamMapper;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuService;

import java.util.LinkedList;
import java.util.List;

@Service
@Profiled
public class SpbuServiceImpl implements SpbuService {

	private final Logger logger = LoggerFactory.getLogger(SpbuServiceImpl.class);

	private final SpbuTeamRepository repository;

	private final SpbuProxy proxy;

	private final SpbuTeamMapper mapper;

	public SpbuServiceImpl(SpbuTeamRepository repository, SpbuProxy proxy, SpbuTeamMapper mapper) {
		this.repository = repository;
		this.proxy = proxy;
		this.mapper = mapper;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveAllAliasGroups(String alias) {
		new Thread(() -> {
			for (SpbuStudyProgramDTO program : studyProgramUnwrap(proxy.getProgramLevels(alias))) {
				try {
					for (SpbuTeamDTO group : proxy.getGroups(program.getProgramId().toString()).getGroups()) {
						group.setAlias(alias);
						var team = mapper.spbuTeamDTOToSpbuTeam(group);
						create(team);

						Thread.sleep(100);
					}
				} catch (NullPointerException | InterruptedException ignored) {
					logger.error("Error occurred while group saving!");
				}

			}
			logger.info("Finished groups saving for alias: " + alias);
		}).start();
	}
}

package ru.studentsplatform.backend.university.schedule.spbu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuStudyProgramDTO;
import ru.studentsplatform.backend.domain.dto.spbu.SpbuTeamDTO;
import ru.studentsplatform.backend.domain.repository.spbu.SpbuTeamRepository;
import ru.studentsplatform.backend.entities.model.spbu.SpbuTeam;
import ru.studentsplatform.backend.service.proxy.FeignConfig;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;
import ru.studentsplatform.backend.university.schedule.spbu.mapper.SpbuTeamMapper;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuTeamService;
import ru.studentsplatform.backend.university.schedule.spbu.service.SpbuUnwrapService;

import java.util.List;

@Slf4j
@Service
@Profiled
public class SpbuTeamServiceImpl implements SpbuTeamService {

	private final SpbuTeamRepository repository;

	private final SpbuTeamMapper mapper;

	private final SpbuUnwrapService unwrapService;

	public SpbuTeamServiceImpl(SpbuTeamRepository repository, SpbuTeamMapper mapper, SpbuUnwrapService unwrapService) {
		this.repository = repository;
		this.mapper = mapper;
		this.unwrapService = unwrapService;
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
	public List<SpbuTeam> getAll() {
		log.info("getting ALL spbu groups from DB...");
		return repository.findAll();
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
		log.info("Initiating groups saving for alias: {}", alias);
		new Thread(() -> {
			try {
				iteratePrograms(alias);
				log.info("Finished groups saving for alias: {}!", alias);

			} catch (NullPointerException | feign.RetryableException e) {
				FeignConfig.changeProxy();
				log.warn("Reattempting to save groups for alias: {}...", alias);
				saveAllAliasGroups(alias);
			}
		}).start();
	}

	/**
	 * Перечисляет список программ обучения, вызывая для каждой метод сохранения групп.
	 *
	 * @param alias Сокращённое наименования направления
	 */
	private void iteratePrograms(String alias) throws feign.RetryableException {
		for (SpbuStudyProgramDTO program : unwrapService.studyProgramUnwrap(
				FeignConfig.getSpbuProxy().getProgramLevels(alias))) {
			try {
				saveGroupList(FeignConfig.getSpbuProxy()
						.getGroups(program.getProgramId().toString()).getGroups(), alias);
			} catch (NullPointerException | InterruptedException ignored) {
				log.error("Error occurred while group saving!");
			}
		}
	}

	/**
	 * Сохраняет список групп.
	 *
	 * @param groups список групп для сохранения
	 * @param alias  сокращённое наименование направления
	 * @throws InterruptedException метод имеет задержку выполнения, требуется обработка
	 */
	private void saveGroupList(List<SpbuTeamDTO> groups, String alias) throws InterruptedException {
		for (SpbuTeamDTO group : groups) {
			log.info("Saving group {}...", group.getName());
			group.setAlias(alias);
			create(mapper.spbuTeamDTOToSpbuTeam(group));

			Thread.sleep(100);
		}
	}

}

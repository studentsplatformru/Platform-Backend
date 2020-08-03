package ru.studentsplatform.backend.service.crud.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.studentsplatform.backend.domain.repository.DisciplineRepository;
import ru.studentsplatform.backend.domain.repository.SubjectRepository;
import ru.studentsplatform.backend.domain.repository.UserRepository;
import ru.studentsplatform.backend.entities.model.university.Discipline;
import ru.studentsplatform.backend.service.crud.DisciplineService;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.system.exception.core.BusinessException;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.List;

/**
 * @author Archie-Vian (sas-aramonov@yandex.ru) 01.08.2020
 */
@Profiled
@Transactional
@Service
public class DisciplineServiceImpl implements DisciplineService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DisciplineServiceImpl.class);

	private final DisciplineRepository disciplineRepository;
	private final UserRepository userRepository;
	private final SubjectRepository subjectRepository;

	public DisciplineServiceImpl(DisciplineRepository disciplineRepository,
								 UserRepository userRepository, SubjectRepository subjectRepository) {
		this.disciplineRepository = disciplineRepository;
		this.userRepository = userRepository;
		this.subjectRepository = subjectRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Discipline create(Discipline newEntity) {
		if (!userRepository.existsById(newEntity.getUser().getId())) {
			throw new BusinessException(ServiceExceptionReason.USER_NOT_FOUND, newEntity.getUser().getId());
		}
		if (!subjectRepository.existsById(newEntity.getSubject().getId())) {
			throw new BusinessException(ServiceExceptionReason.SUBJECT_NOT_FOUND, newEntity.getSubject().getId());
		}
		return disciplineRepository.save(newEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Discipline getById(Long id) {
		return disciplineRepository.findById(id).orElseThrow(() ->
				new BusinessException(ServiceExceptionReason.DISCIPLINE_NOT_FOUND, id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Discipline> getAll() {
		return disciplineRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Discipline update(Discipline updatedEntity, Long id) {
		updatedEntity.setId(id);
		return disciplineRepository.save(updatedEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(Long id) {
		try {
			disciplineRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Error occured: cannot delete non-existent discipline");
			return false;
		}
		return true;
	}
}

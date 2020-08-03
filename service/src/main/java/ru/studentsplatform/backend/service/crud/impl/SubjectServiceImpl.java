package ru.studentsplatform.backend.service.crud.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.studentsplatform.backend.domain.repository.SubjectRepository;
import ru.studentsplatform.backend.entities.model.university.Subject;
import ru.studentsplatform.backend.service.crud.SubjectService;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.system.exception.core.BusinessException;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.List;

@Profiled
@Transactional
@Service
public class SubjectServiceImpl implements SubjectService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SubjectServiceImpl.class);

	private final SubjectRepository subjectRepository;

	/**
	 * Конструктор.
	 *
	 * @param subjectRepository репозиторий пользователь
	 */
	public SubjectServiceImpl(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Subject create(Subject newEntity) {
		return subjectRepository.save(newEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Subject getById(Long id) {
		return subjectRepository.findById(id).orElseThrow(() ->
				new BusinessException(ServiceExceptionReason.SUBJECT_NOT_FOUND, id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Subject> getAll() {
		return subjectRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Subject update(Subject updatedEntity, Long id) {
		if (!subjectRepository.existsById(id)) {
			throw new BusinessException(ServiceExceptionReason.SUBJECT_NOT_FOUND, id);
		}
		updatedEntity.setId(id);
		return subjectRepository.saveAndFlush(updatedEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(Long id) {
		try {
			subjectRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Error occured: cannot delete non-existent subject");
			return false;
		}
		return true;
	}
}

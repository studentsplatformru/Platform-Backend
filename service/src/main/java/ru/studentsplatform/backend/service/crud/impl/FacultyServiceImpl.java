package ru.studentsplatform.backend.service.crud.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.studentsplatform.backend.domain.repository.FacultyRepository;
import ru.studentsplatform.backend.domain.repository.UniversityRepository;
import ru.studentsplatform.backend.entities.model.university.Faculty;
import ru.studentsplatform.backend.service.crud.FacultyService;
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
public class FacultyServiceImpl implements FacultyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FacultyServiceImpl.class);

	private final FacultyRepository facultyRepository;
	private final UniversityRepository universityRepository;

	public FacultyServiceImpl(FacultyRepository facultyRepository, UniversityRepository universityRepository) {
		this.facultyRepository = facultyRepository;
		this.universityRepository = universityRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Faculty create(Faculty newEntity) {
		if (!universityRepository.existsById(newEntity.getUniversity().getId())) {
			throw new BusinessException(ServiceExceptionReason.UNIVERSITY_NOT_FOUND,
					newEntity.getUniversity().getId());
		}
		return facultyRepository.save(newEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Faculty getById(Long id) {
		return facultyRepository.findById(id).orElseThrow(() ->
				new BusinessException(ServiceExceptionReason.FACULTY_NOT_FOUND, id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Faculty> getAll() {
		return facultyRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Faculty update(Faculty updatedEntity, Long id) {
		updatedEntity.setId(id);
		return facultyRepository.save(updatedEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(Long id) {
		try {
			facultyRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Error occured: cannot delete non-existent faculty");
			return false;
		}
		return true;
	}
}

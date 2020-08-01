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
import ru.studentsplatform.backend.system.annotation.Profiled;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import java.util.List;

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

    @Override
    public Discipline getById(Long id) {
        return disciplineRepository.findById(id).orElseThrow(() ->
                new BusinessException(ServiceExceptionReason.DISCIPLINE_NOT_FOUND, id));
    }

    @Override
    public List<Discipline> getAll() {
        return disciplineRepository.findAll();
    }

    @Override
    public Discipline update(Discipline updatedEntity, Long id) {
        updatedEntity.setId(id);
        return disciplineRepository.save(updatedEntity);
    }

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

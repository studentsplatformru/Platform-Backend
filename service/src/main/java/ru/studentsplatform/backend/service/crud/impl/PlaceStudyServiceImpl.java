package ru.studentsplatform.backend.service.crud.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.domain.repository.FacultyRepository;
import ru.studentsplatform.backend.domain.repository.PlaceStudyRepository;
import ru.studentsplatform.backend.domain.repository.UniversityRepository;
import ru.studentsplatform.backend.domain.repository.DepartmentRepository;
import ru.studentsplatform.backend.domain.repository.DirectionRepository;
import ru.studentsplatform.backend.domain.repository.TeamRepository;
import ru.studentsplatform.backend.domain.repository.UserRepository;
import ru.studentsplatform.backend.entities.model.university.PlaceStudy;
import ru.studentsplatform.backend.service.crud.PlaceStudyService;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import java.util.List;

/**
 * @author Perevalov Pavel (28.07.2020)
 */
@Service
public class PlaceStudyServiceImpl implements PlaceStudyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceStudyServiceImpl.class);

    private final PlaceStudyRepository placeStudyRepository;
    private final UserRepository userRepository;
    private final UniversityRepository universityRepository;
    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;
    private final DirectionRepository directionRepository;
    private final TeamRepository teamRepository;

    /**
     * Конструктор.
     * @param placeStudyRepository PlaceStudy репозиторий
     * @param userRepository User репозитори
     * @param universityRepository University репозиторий
     * @param facultyRepository Faculty репозиторий
     * @param departmentRepository Department репозиторий
     * @param directionRepository Direction репозиторий
     * @param teamRepository Team репозиторий
     */
    public PlaceStudyServiceImpl(PlaceStudyRepository placeStudyRepository,
                                 UserRepository userRepository,
                                 UniversityRepository universityRepository,
                                 FacultyRepository facultyRepository,
                                 DepartmentRepository departmentRepository,
                                 DirectionRepository directionRepository,
                                 TeamRepository teamRepository) {
        this.placeStudyRepository = placeStudyRepository;
        this.userRepository = userRepository;
        this.universityRepository = universityRepository;
        this.facultyRepository = facultyRepository;
        this.departmentRepository = departmentRepository;
        this.directionRepository = directionRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public PlaceStudy create(PlaceStudy newPlaceStudy) {
        if (!userRepository.existsById(newPlaceStudy.getUser().getId())) {
            throw new BusinessException(ServiceExceptionReason.USER_NOT_FOUND,
                    newPlaceStudy.getUser().getId());
        }
        if (!universityRepository.existsById(newPlaceStudy.getUniversity().getId())) {
            throw new BusinessException(ServiceExceptionReason.SUBJECT_NOT_FOUND,
                    newPlaceStudy.getUniversity().getId());
        }
        if (!facultyRepository.existsById(newPlaceStudy.getFaculty().getId())) {
            throw new BusinessException(ServiceExceptionReason.UNIVERSITY_NOT_FOUND,
                    newPlaceStudy.getUniversity().getId());
        }
        if (!departmentRepository.existsById(newPlaceStudy.getDepartment().getId())) {
            throw new BusinessException(ServiceExceptionReason.DEPARTMENT_NOT_FOUND,
                    newPlaceStudy.getDepartment().getId());
        }
        if (!directionRepository.existsById(newPlaceStudy.getDirection().getId())) {
            throw new BusinessException(ServiceExceptionReason.DIRECTION_NOT_FOUND,
                    newPlaceStudy.getDirection().getId());
        }
        if (!teamRepository.existsById(newPlaceStudy.getTeam().getId())) {
            throw new BusinessException(ServiceExceptionReason.TEAM_NOT_FOUND,
                    newPlaceStudy.getTeam().getId());
        }
        newPlaceStudy.setUser(userRepository.getOne(newPlaceStudy.getId()));
        return placeStudyRepository.saveAndFlush(newPlaceStudy);
    }

    @Override
    public PlaceStudy getById(Long id) {
        return placeStudyRepository.findById(id).orElseThrow(() ->
                new BusinessException(ServiceExceptionReason.PLACE_STUDY_NOT_FOUND, id));
    }

    @Override
    public List<PlaceStudy> getAll() {
        return placeStudyRepository.findAll();
    }

    @Override
    public PlaceStudy update(PlaceStudy updatedPlaceStudy, Long id) {
        updatedPlaceStudy.setId(id);
        return placeStudyRepository.save(updatedPlaceStudy);
    }

    @Override
    public boolean delete(Long id) {
        try {
            placeStudyRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Error occured: cannot delete non-existent place study");
            return false;
        }
        return true;
    }
}

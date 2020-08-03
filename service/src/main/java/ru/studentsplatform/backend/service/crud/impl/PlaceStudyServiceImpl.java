package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.domain.repository.PlaceStudyRepository;
import ru.studentsplatform.backend.entities.model.university.PlaceStudy;
import ru.studentsplatform.backend.service.crud.PlaceStudyService;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Perevalov Pavel (28.07.2020)
 */
@Service
public class PlaceStudyServiceImpl implements PlaceStudyService {
    private final PlaceStudyRepository placeStudyRepository;

    public PlaceStudyServiceImpl(PlaceStudyRepository placeStudyRepository) {
        this.placeStudyRepository = placeStudyRepository;
    }

    @Override
    public PlaceStudy create(PlaceStudy newPlaceStudy) {
        return placeStudyRepository.saveAndFlush(newPlaceStudy);
    }

    @Override
    public PlaceStudy getById(Long id) {
        return placeStudyRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<PlaceStudy> getAll() {
        return placeStudyRepository.findAll();
    }

    @Override
    public PlaceStudy update(PlaceStudy updatedPlaceStudy, Long id) {
        updatedPlaceStudy.setId(id);
        return placeStudyRepository.saveAndFlush(updatedPlaceStudy);
    }

    @Override
    public boolean delete(Long id) {
        try {
            placeStudyRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

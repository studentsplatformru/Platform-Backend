package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.entities.model.JobAd;
import ru.studentsplatform.backend.repository.JobAdRepository;
import ru.studentsplatform.backend.service.crud.JobAdService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class JobAdServiceImpl implements JobAdService {

    private final JobAdRepository jobAdRepository;

    public JobAdServiceImpl(JobAdRepository jobAdRepository) {
        this.jobAdRepository = jobAdRepository;
    }

    @Override
    public JobAd create(JobAd newEntity) {
        return jobAdRepository.saveAndFlush(newEntity);
    }

    @Override
    public JobAd getById(Long id) {
        return jobAdRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<JobAd> getAll() {
        return jobAdRepository.findAll();
    }

    @Override
    public JobAd update(JobAd updatedEntity, Long id) {
        updatedEntity.setId(id);
        return jobAdRepository.saveAndFlush(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            jobAdRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

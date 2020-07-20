package ru.studentsplatform.backend.service.crud.impl;

import ru.studentsplatform.backend.entities.model.JobAd;
import ru.studentsplatform.backend.repository.JobAdRepository;
import ru.studentsplatform.backend.service.crud.JobAdService;

import java.util.List;
import java.util.NoSuchElementException;

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
        JobAd jobAd = jobAdRepository.findById(id).orElseThrow(NoSuchElementException::new);
        jobAd.setCategory(updatedEntity.getCategory());
        jobAd.setDescription(updatedEntity.getDescription());
        jobAd.setFaculty(updatedEntity.getFaculty());
        jobAd.setJobName(updatedEntity.getJobName());
        jobAd.setLink(updatedEntity.getLink());
        return jobAdRepository.saveAndFlush(jobAd);
    }

    @Override
    public boolean delete(Long id) {
        if (jobAdRepository.findById(id).isEmpty()) {
            return false;
        }
        jobAdRepository.deleteById(id);
        return true;
    }
}

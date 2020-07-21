package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.JobAdDTO;
import ru.studentsplatform.backend.endpoint.crud.JobAdController;
import ru.studentsplatform.backend.entities.model.JobAd;
import ru.studentsplatform.backend.mapper.JobAdMapper;
import ru.studentsplatform.backend.service.crud.JobAdService;

import java.util.List;

@RestController
@RequestMapping(JobAdController.BASE_URL)
public class JobAdControllerImpl implements JobAdController {

    private final JobAdService jobAdService;

    private final JobAdMapper mapper;

    public JobAdControllerImpl(JobAdService jobAdService, JobAdMapper mapper) {
        this.jobAdService = jobAdService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<JobAdDTO> create(JobAdDTO newInstanceRequest) {
        JobAd jobAd = mapper.jobAdDTOtoJobAd(newInstanceRequest);
        jobAd = jobAdService.create(jobAd);
        return ResponseEntity.ok(mapper.jobAdToJobAdDTO(jobAd));
    }

    @Override
    public ResponseEntity<JobAdDTO> getById(Long id) {
        JobAd jobAd = jobAdService.getById(id);
        JobAdDTO jobAdDTO = mapper.jobAdToJobAdDTO(jobAd);
        return ResponseEntity.ok(jobAdDTO);
    }

    @Override
    public ResponseEntity<List<JobAdDTO>> getAll() {
        List<JobAd> jobAdList = jobAdService.getAll();
        List<JobAdDTO> jobAdDTOList = mapper.listJobAdToJobAdDTO(jobAdList);
        return ResponseEntity.ok(jobAdDTOList);
    }

    @Override
    public ResponseEntity<JobAdDTO> update(JobAdDTO updatedInstanceRequest, Long id) {
        JobAd jobAd = mapper.jobAdDTOtoJobAd(updatedInstanceRequest);
        jobAd = jobAdService.update(jobAd, id);
        return ResponseEntity.ok(mapper.jobAdToJobAdDTO(jobAd));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(jobAdService.delete(id));
    }
}

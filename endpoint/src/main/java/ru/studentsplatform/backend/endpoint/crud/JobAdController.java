package ru.studentsplatform.backend.endpoint.crud;

import ru.studentsplatform.backend.dto.JobAdDTO;

public interface JobAdController extends AbstractController<JobAdDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/job_ad";
}

package ru.studentsplatform.backend.service.crud;

import ru.studentsplatform.backend.entities.model.university.PlaceStudy;

import java.util.List;

public interface PlaceStudyService extends AbstractService<PlaceStudy> {

    @Override
    PlaceStudy create(PlaceStudy newEntity);

    @Override
    PlaceStudy getById(Long id);

    @Override
    List<PlaceStudy> getAll();

    @Override
    PlaceStudy update(PlaceStudy updatedEntity, Long id);

    @Override
    boolean delete(Long id);
}

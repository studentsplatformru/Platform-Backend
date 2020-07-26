package ru.studentsplatform.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studentsplatform.backend.entities.model.university.PlaceStudy;

@Repository
public interface PlaceStudyRepository extends JpaRepository<PlaceStudy, Long> {
}

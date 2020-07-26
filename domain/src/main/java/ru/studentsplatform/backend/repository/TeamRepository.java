package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studentsplatform.backend.entities.model.university.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
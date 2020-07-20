package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studentsplatform.backend.entities.model.StudentCouncil;
import ru.studentsplatform.backend.entities.model.Team;

@Repository
public interface StudentCouncilRepository extends JpaRepository<StudentCouncil, Long> {
}
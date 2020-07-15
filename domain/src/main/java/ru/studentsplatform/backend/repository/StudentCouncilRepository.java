package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.Group;

public interface StudentCouncilRepository extends JpaRepository<Group, Long> {

}
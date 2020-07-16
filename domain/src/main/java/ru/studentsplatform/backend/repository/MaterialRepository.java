package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}

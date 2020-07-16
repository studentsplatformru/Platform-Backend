package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}

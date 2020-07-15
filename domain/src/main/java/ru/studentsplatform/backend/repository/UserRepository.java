package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentsplatform.backend.entities.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

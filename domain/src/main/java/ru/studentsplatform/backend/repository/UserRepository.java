package ru.studentsplatform.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studentsplatform.backend.entities.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

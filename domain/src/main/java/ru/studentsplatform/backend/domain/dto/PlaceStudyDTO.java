package ru.studentsplatform.backend.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.studentsplatform.backend.entities.model.university.Department;
import ru.studentsplatform.backend.entities.model.university.Direction;
import ru.studentsplatform.backend.entities.model.university.Faculty;
import ru.studentsplatform.backend.entities.model.university.Team;
import ru.studentsplatform.backend.entities.model.university.University;
import ru.studentsplatform.backend.entities.model.user.User;

/**
 * Класс DTO, хранящий сведения о сущности места учёбы.
 *
 * @author Perevalov Pavel 28.07.2020
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PlaceStudyDTO extends BaseDTO {

    private User user;

    private University university;

    private Faculty faculty;

    private Department department;

    private Direction direction;

    private Team team;

    private int semester;
}

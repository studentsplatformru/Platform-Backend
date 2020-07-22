package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.StudentDTO;
import ru.studentsplatform.backend.entities.model.Student;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, DepartmentMapper.class, DirectionMapper.class, TeamMapper.class})
public interface StudentMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "userId", source = "entity.user.id"),
            @Mapping(target = "departmentId", source = "entity.department.id"),
            @Mapping(target = "teamId", source = "entity.team.id"),
            @Mapping(target = "directionId", source = "entity.direction.id")
    })
    StudentDTO studentToStudentDTO(Student entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "user.id", source = "dto.userId"),
            @Mapping(target = "department.id", source = "dto.departmentId"),
            @Mapping(target = "team.id", source = "dto.teamId"),
            @Mapping(target = "direction.id", source = "dto.directionId")
    })
    Student studentDTOtoStudent(StudentDTO dto);

    List<StudentDTO> listStudentToStudentDTO(List<Student> entity);
    List<Student> listStudentDTOtoStudent(List<StudentDTO> dto);

}

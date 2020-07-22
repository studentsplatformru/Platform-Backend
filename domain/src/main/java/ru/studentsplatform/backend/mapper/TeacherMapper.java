package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.TeacherDTO;
import ru.studentsplatform.backend.entities.model.Teacher;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, DepartmentMapper.class, DirectionMapper.class})
public interface TeacherMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "userId", source = "entity.user.id"),
            @Mapping(target = "personalPage", source = "entity.personalPage"),
            @Mapping(target = "departmentId", source = "entity.department.id"),
            @Mapping(target = "directionId", source = "entity.direction.id")
    })
    TeacherDTO teacherToTeacherDTO(Teacher entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "user.id", source = "dto.userId"),
            @Mapping(target = "personalPage", source = "dto.personalPage"),
            @Mapping(target = "department.id", source = "dto.departmentId"),
            @Mapping(target = "direction.id", source = "dto.directionId")
    })
    Teacher teacherDTOtoTeacher(TeacherDTO dto);

    List<TeacherDTO> listTeacherToTeacherDTO(List<Teacher> entity);
    List<Teacher> listTeacherDTOtoTeacher(List<TeacherDTO> dto);

}

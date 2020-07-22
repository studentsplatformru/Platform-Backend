package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.TeacherDTO;
import ru.studentsplatform.backend.entities.model.Teacher;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class, DirectionMapper.class})
public interface TeacherMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "personalPage", source = "entity.personalPage"),
            @Mapping(target = "user", source = "entity.user"),
            @Mapping(target = "departmentId", source = "entity.department.id"),
            @Mapping(target = "directionId", source = "entity.direction.id")
    })
    TeacherDTO teacherToTeacherDTO(Teacher entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "personalPage", source = "dto.personalPage"),
            @Mapping(target = "user", source = "dto.user"),
            @Mapping(target = "department.id", source = "dto.departmentId"),
            @Mapping(target = "direction.id", source = "dto.directionId")
    })
    Teacher teacherDTOtoTeacher(TeacherDTO dto);

    List<TeacherDTO> listTeacherToTeacherDTO(List<Teacher> entity);
    List<Teacher> listTeacherDTOtoTeacher(List<TeacherDTO> dto);

}

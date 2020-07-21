package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.TeacherDTO;
import ru.studentsplatform.backend.entities.model.Teacher;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "personalPage", source = "entity.personalPage"),
            @Mapping(target = "user", source = "entity.user"),
            @Mapping(target = "department", source = "entity.department"),
            @Mapping(target = "teachersFeedback", source = "entity.teachersFeedback"),
            @Mapping(target = "lessonUnits", source = "entity.lessonUnits"),
            @Mapping(target = "direction", source = "entity.direction")
    })
    TeacherDTO teacherToTeacherDTO(Teacher entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "personalPage", source = "dto.personalPage"),
            @Mapping(target = "user", source = "dto.user"),
            @Mapping(target = "department", source = "dto.department"),
            @Mapping(target = "teachersFeedback", source = "dto.teachersFeedback"),
            @Mapping(target = "lessonUnits", source = "dto.lessonUnits"),
            @Mapping(target = "direction", source = "dto.direction")
    })
    Teacher teacherDTOtoTeacher(TeacherDTO dto);

    List<TeacherDTO> listTeacherToTeacherDTO(List<Teacher> entity);
    List<Teacher> listTeacherDTOtoTeacher(List<TeacherDTO> dto);

}

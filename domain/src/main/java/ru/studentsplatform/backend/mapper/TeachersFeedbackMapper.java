package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.TeachersFeedbackDTO;
import ru.studentsplatform.backend.entities.model.TeachersFeedback;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {UserMapper.class, TeacherMapper.class})
public interface TeachersFeedbackMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "header", source = "entity.header"),
            @Mapping(target = "content", source = "entity.content"),
            @Mapping(target = "authorId", source = "entity.author.id"),
            @Mapping(target = "teacherId", source = "entity.teacher.id")
    })
    TeachersFeedbackDTO teachersFeedbackToTeachersFeedbackDTO(TeachersFeedback entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "header", source = "dto.header"),
            @Mapping(target = "content", source = "dto.content"),
            @Mapping(target = "author.id", source = "dto.authorId"),
            @Mapping(target = "teacher.id", source = "dto.teacherId")
    })
    TeachersFeedback teachersFeedbackDTOtoTeachersFeedback(TeachersFeedbackDTO dto);

    List<TeachersFeedbackDTO> listTeachersFeedbackToTeachersFeedbackDTO(List<TeachersFeedback> entity);
    List<TeachersFeedback> listTeachersFeedbackDTOtoTeachersFeedback(List<TeachersFeedbackDTO> dto);
    Set<TeachersFeedbackDTO> setTeachersFeedbackToTeachersFeedbackDTO(Set<TeachersFeedback> entity);
    Set<TeachersFeedback> setTeachersFeedbackDTOtoTeachersFeedback(Set<TeachersFeedbackDTO> dto);

}

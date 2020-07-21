package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.SubjectDTO;
import ru.studentsplatform.backend.entities.model.Subject;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "subjectName", source = "entity.subjectName"),
            @Mapping(target = "team", source = "entity.team"),
            @Mapping(target = "lessonUnits", source = "entity.lessonUnits"),
            @Mapping(target = "materials", source = "entity.materials")
    })
    SubjectDTO subjectToSubjectDTO(Subject entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "subjectName", source = "dto.subjectName"),
            @Mapping(target = "team", source = "dto.team"),
            @Mapping(target = "lessonUnits", source = "dto.lessonUnits"),
            @Mapping(target = "materials", source = "dto.materials")
    })
    Subject subjectDTOtoSubject(SubjectDTO dto);

    List<SubjectDTO> listSubjectToSubjectDTO(List<Subject> entity);
    List<Subject> listSubjectDTOtoSubject(List<SubjectDTO> dto);

}

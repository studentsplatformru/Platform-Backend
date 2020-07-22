package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.SubjectDTO;
import ru.studentsplatform.backend.entities.model.Subject;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TeamMapper.class})
public interface SubjectMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "subjectName", source = "entity.subjectName"),
            @Mapping(target = "teamId", source = "entity.team.id"),
            @Mapping(target = "lessonUnitsDTO", source = "entity.lessonUnitsDTO"),
            @Mapping(target = "materialsDTO", source = "entity.materialsDTO")
    })
    SubjectDTO subjectToSubjectDTO(Subject entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "subjectName", source = "dto.subjectName"),
            @Mapping(target = "team.id", source = "dto.teamId"),
            @Mapping(target = "lessonUnitsDTO", source = "dto.lessonUnitsDTO"),
            @Mapping(target = "materialsDTO", source = "dto.materialsDTO")
    })
    Subject subjectDTOtoSubject(SubjectDTO dto);

    List<SubjectDTO> listSubjectToSubjectDTO(List<Subject> entity);
    List<Subject> listSubjectDTOtoSubject(List<SubjectDTO> dto);

}

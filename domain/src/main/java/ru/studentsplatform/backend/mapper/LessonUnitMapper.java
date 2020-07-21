package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.LessonUnitDTO;
import ru.studentsplatform.backend.entities.model.LessonUnit;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SubjectMapper.class, TeacherMapper.class})
public interface LessonUnitMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "startTime", source = "entity.startTime"),
            @Mapping(target = "endTime", source = "entity.endTime"),
            @Mapping(target = "audience", source = "entity.audience"),
            @Mapping(target = "type", source = "entity.type"),
            @Mapping(target = "note", source = "entity.note"),
            @Mapping(target = "teacher", source = "entity.teacher"),
            @Mapping(target = "subject", source = "entity.subject")

    })
    LessonUnitDTO lessonUnitToLessonUnitDTO(LessonUnit entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "startTime", source = "dto.startTime"),
            @Mapping(target = "endTime", source = "dto.endTime"),
            @Mapping(target = "audience", source = "dto.audience"),
            @Mapping(target = "type", source = "dto.type"),
            @Mapping(target = "note", source = "dto.note"),
            @Mapping(target = "teacher", source = "dto.teacher"),
            @Mapping(target = "subject", source = "dto.subject")

    })
    LessonUnit lessonUnitDTOToLessonUnit(LessonUnitDTO dto);

    List<LessonUnitDTO> listLessonUnitToLessonUnitDTO(List<LessonUnit> entity);

    List<LessonUnit> listLessonUnitDTOtoLessonUnit(List<LessonUnitDTO> dto);

}

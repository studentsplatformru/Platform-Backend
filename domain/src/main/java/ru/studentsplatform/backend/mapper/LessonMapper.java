package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.LessonDTO;
import ru.studentsplatform.backend.entities.model.Lesson;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LessonUnitMapper.class, TeamMapper.class})
public interface LessonMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "date", source = "entity.date"),
            @Mapping(target = "teamId", source = "entity.team.id"),
            @Mapping(target = "lessonUnitId", source = "entity.lessonUnit.id"),
            @Mapping(target = "homeworkDTO", source = "entity.homeworkList"),
            @Mapping(target = "marksDTO", source = "entity.marks")
    })
    LessonDTO lessonToLessonDTO(Lesson entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "date", source = "dto.date"),
            @Mapping(target = "team.id", source = "dto.teamId"),
            @Mapping(target = "lessonUnit.id", source = "dto.lessonUnitId"),
            @Mapping(target = "homeworkList", source = "dto.homeworkDTO"),
            @Mapping(target = "marks", source = "dto.marksDTO")

    })
    Lesson lessonDTOtoLesson(LessonDTO dto);

    List<LessonDTO> listLessonToLessonDTO(List<Lesson> entity);
    List<Lesson> listLessonDTOtoLesson(List<LessonDTO> dto);

}

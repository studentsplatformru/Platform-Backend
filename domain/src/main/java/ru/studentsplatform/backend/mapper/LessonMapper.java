package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.LessonDTO;
import ru.studentsplatform.backend.entities.model.Lesson;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "date", source = "entity.date"),
            @Mapping(target = "homeworkList", source = "entity.homeworkList"),
            @Mapping(target = "marks", source = "entity.marks"),
            @Mapping(target = "attendanceList", source = "entity.attendanceList"),
            @Mapping(target = "team", source = "entity.team"),
            @Mapping(target = "lessonUnit", source = "entity.lessonUnit")
    })
    LessonDTO lessonToLessonDTO(Lesson entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "date", source = "dto.date"),
            @Mapping(target = "homeworkList", source = "dto.homeworkList"),
            @Mapping(target = "marks", source = "dto.marks"),
            @Mapping(target = "attendanceList", source = "dto.attendanceList"),
            @Mapping(target = "team", source = "dto.team"),
            @Mapping(target = "lessonUnit", source = "dto.lessonUnit")
    })
    Lesson lessonDTOtoLesson(LessonDTO dto);

    List<LessonDTO> listLessonToLessonDTO(List<Lesson> entity);
    List<Lesson> listLessonDTOtoLesson(List<LessonDTO> dto);

}

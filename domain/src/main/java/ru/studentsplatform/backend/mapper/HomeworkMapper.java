package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.HomeworkDTO;
import ru.studentsplatform.backend.entities.model.Homework;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LessonMapper.class})
public interface HomeworkMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "lesson", source = "entity.lesson"),
            @Mapping(target = "fileType", source = "entity.fileType"),
            @Mapping(target = "file", source = "entity.file"),
            @Mapping(target = "note", source = "entity.note")
    })
    HomeworkDTO homeworkToHomeworkDTO(Homework entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "lesson", source = "dto.lesson"),
            @Mapping(target = "fileType", source = "dto.fileType"),
            @Mapping(target = "file", source = "dto.file"),
            @Mapping(target = "note", source = "dto.note")
    })
    Homework homeworkDTOtoHomework(HomeworkDTO dto);

    List<HomeworkDTO> listHomeworkToHomeworkDTO(List<Homework> entity);
    List<Homework> listHomeworkDTOtoHomework(List<HomeworkDTO> dto);

}

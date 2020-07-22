package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.StudentCouncilDTO;
import ru.studentsplatform.backend.entities.model.StudentCouncil;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FacultyMapper.class})
public interface StudentCouncilMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "phone", source = "entity.phone"),
            @Mapping(target = "email", source = "entity.email"),
            @Mapping(target = "audience", source = "entity.audience"),
            @Mapping(target = "vkGroup", source = "entity.vkGroup"),
            @Mapping(target = "facultyId", source = "entity.faculty.id")
    })
    StudentCouncilDTO studentCouncilToStudentCouncilDTO(StudentCouncil entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "phone", source = "dto.phone"),
            @Mapping(target = "email", source = "dto.email"),
            @Mapping(target = "audience", source = "dto.audience"),
            @Mapping(target = "vkGroup", source = "dto.vkGroup"),
            @Mapping(target = "faculty.id", source = "dto.facultyId")
    })
    StudentCouncil studentCouncilDTOtoStudentCouncil(StudentCouncilDTO dto);

    List<StudentCouncilDTO> listStudentCouncilToStudentCouncilDTO(List<StudentCouncil> entity);
    List<StudentCouncil> listStudentCouncilDTOtoStudentCouncil(List<StudentCouncilDTO> dto);

}

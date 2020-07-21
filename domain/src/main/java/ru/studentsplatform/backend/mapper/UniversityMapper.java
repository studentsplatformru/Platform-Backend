package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.UniversityDTO;
import ru.studentsplatform.backend.entities.model.University;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UniversityMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "universityName", source = "entity.universityName"),
            @Mapping(target = "faculties", source = "entity.faculties"),
            @Mapping(target = "libraries", source = "entity.libraries")
    })
    UniversityDTO directionToDirectionDTO(University entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "universityName", source = "dto.universityName"),
            @Mapping(target = "faculties", source = "dto.faculties"),
            @Mapping(target = "libraries", source = "dto.libraries")

    })
    University directionDTOtoDirection(UniversityDTO dto);

    List<UniversityDTO> listDepartmentToDepartmentDTO(List<University> entity);

    List<University> listDepartmentDTOtoDepartment(List<UniversityDTO> dto);

}

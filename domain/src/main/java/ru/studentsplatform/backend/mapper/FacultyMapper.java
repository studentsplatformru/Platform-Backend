package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.FacultyDTO;
import ru.studentsplatform.backend.entities.model.Faculty;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {UniversityMapper.class})
public interface FacultyMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "facultyName", source = "entity.facultyName"),
            @Mapping(target = "universityName", source = "entity.university.universityName"),
            @Mapping(target = "jobAdsDTO", source = "entity.jobAdsDTO"),
            @Mapping(target = "directionsDTO", source = "entity.directionsDTO")
    })
    FacultyDTO facultyToFacultyDTO(Faculty entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "facultyName", source = "dto.facultyName"),
            @Mapping(target = "university.universityName", source = "dto.universityName"),
            @Mapping(target = "jobAdsDTO", source = "dto.jobAdsDTO"),
            @Mapping(target = "directionsDTO", source = "dto.directionsDTO")
    })
    Faculty facultyDTOtoFaculty(FacultyDTO dto);

    List<FacultyDTO> listFacultyToFacultyDTO(List<Faculty> entity);
    List<Faculty> listFacultyDTOtoFaculty(List<FacultyDTO> dto);
    Set<FacultyDTO> setFacultyToFacultyDTO(Set<Faculty> entity);
    Set<Faculty> setFacultyDTOtoFaculty(Set<FacultyDTO> dto);
}

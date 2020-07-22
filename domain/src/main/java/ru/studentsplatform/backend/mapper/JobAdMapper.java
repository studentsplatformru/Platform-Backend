package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.JobAdDTO;
import ru.studentsplatform.backend.entities.model.JobAd;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FacultyMapper.class})
public interface JobAdMapper {
    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "category", source = "entity.category"),
            @Mapping(target = "jobName", source = "entity.jobName"),
            @Mapping(target = "description", source = "entity.description"),
            @Mapping(target = "link", source = "entity.link"),
            @Mapping(target = "facultyId", source = "entity.faculty.id")
    })
    JobAdDTO jobAdToJobAdDTO(JobAd entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "category", source = "dto.category"),
            @Mapping(target = "jobName", source = "dto.jobName"),
            @Mapping(target = "description", source = "dto.description"),
            @Mapping(target = "link", source = "dto.link"),
            @Mapping(target = "faculty.id", source = "dto.facultyId")
    })
    JobAd jobAdDTOtoJobAd(JobAdDTO dto);

    List<JobAdDTO> listJobAdToJobAdDTO(List<JobAd> entity);
    List<JobAd> listJobAdDTOtoJobAd(List<JobAdDTO> dto);
}

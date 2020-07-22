package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.TeamDTO;
import ru.studentsplatform.backend.entities.model.Team;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {DirectionMapper.class})
public interface TeamMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "course", source = "entity.course"),
            @Mapping(target = "teamName", source = "entity.teamName"),
            @Mapping(target = "directionId", source = "entity.direction.id"),
            @Mapping(target = "studentsDTO", source = "entity.students"),
            @Mapping(target = "lessonsDTO", source = "entity.lessons"),
            @Mapping(target = "subjectsDTO", source = "entity.subjects")
    })
    TeamDTO teamToTeamDTO(Team entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "course", source = "dto.course"),
            @Mapping(target = "teamName", source = "dto.teamName"),
            @Mapping(target = "direction.id", source = "dto.directionId"),
            @Mapping(target = "students", source = "dto.studentsDTO"),
            @Mapping(target = "lessons", source = "dto.lessonsDTO"),
            @Mapping(target = "subjects", source = "dto.subjectsDTO")
    })
    Team teamDTOtoTeam(TeamDTO dto);

    List<TeamDTO> listTeamToTeamDTO(List<Team> entity);
    List<Team> listTeamDTOtoTeam(List<TeamDTO> dto);
    Set<TeamDTO> setTeamToTeamDTO(Set<Team> entity);
    Set<Team> setTeamDTOtoTeam(Set<Team> dto);

}

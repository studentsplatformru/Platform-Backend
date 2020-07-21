package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.TeamDTO;
import ru.studentsplatform.backend.entities.model.Team;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "course", source = "entity.course"),
            @Mapping(target = "teamName", source = "entity.teamName"),
            @Mapping(target = "students", source = "entity.students"),
            @Mapping(target = "lessons", source = "entity.lessons"),
            @Mapping(target = "subjects", source = "entity.subjects"),
            @Mapping(target = "direction", source = "entity.direction")
    })
    TeamDTO teamToTeamDTO(Team entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "course", source = "dto.course"),
            @Mapping(target = "teamName", source = "dto.teamName"),
            @Mapping(target = "students", source = "dto.students"),
            @Mapping(target = "lessons", source = "dto.lessons"),
            @Mapping(target = "subjects", source = "dto.subjects"),
            @Mapping(target = "direction", source = "dto.direction")
    })
    Team teamDTOtoTeam(TeamDTO dto);

    List<TeamDTO> listTeamToTeamDTO(List<Team> entity);
    List<Team> listTeamDTOtoTeam(List<TeamDTO> dto);

}

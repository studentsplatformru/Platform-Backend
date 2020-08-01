package ru.studentsplatform.backend.endpoint.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.university.TeamDTO;
import ru.studentsplatform.backend.entities.model.university.Team;

import java.util.List;

/**
 * Маппер для конвертации объекта сущности группы студентов в DTO,
 * а также List'ов, содержащих сущности в List DTO и обратно.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 31.07.2020
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TeamMapper {
    /**
     * Трансформация полей сущности группы в поля DTO.
     *
     * @param entity Сущность группы, на основе которой будет создан DTO
     * @return DTO группы.
     */
    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "semester", source = "entity.semester"),
            @Mapping(target = "teamName", source = "entity.teamName"),
            @Mapping(target = "users", source = "entity.users"),
            @Mapping(target = "directionId", source = "entity.direction.id")

    })
    TeamDTO teamToTeamDTO(Team entity);

    /**
     * Трансформация полей объекта DTO группы в поля сущности группы.
     *
     * @param dto DTO группы, на основе которого будет создана сущность.
     * @return Объект сущности группы.
     */
    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "semester", source = "dto.semester"),
            @Mapping(target = "teamName", source = "dto.teamName"),
            @Mapping(target = "users", source = "dto.users"),
            @Mapping(target = "direction.id", source = "dto.directionId")

    })
    Team teamDTOToTeam(TeamDTO dto);

    /**
     * Конвертирует сразу несколько Сущностей в DTO.
     *
     * @param entity Список групп.
     * @return Список DTO.
     */
    List<TeamDTO> listTeamToTeamDTO(List<Team> entity);

    /**
     * Конвертирует сразу несколько DTO в сущности групп.
     *
     * @param dto Список DTO.
     * @return Список групп
     */
    List<Team> listTeamDTOToTeam(List<TeamDTO> dto);

}

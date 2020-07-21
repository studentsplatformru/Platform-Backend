package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.DirectionDTO;
import ru.studentsplatform.backend.entities.model.Direction;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface DirectionMapper {
    @Mappings({
            @Mapping(target = "directionName", source = "entity.directionName"),
            @Mapping(target = "directionCode", source = "entity.directionCode"),
            @Mapping(target = "faculty", source = "entity.faculty"),
            @Mapping(target = "teams", source = "entity.teams")
    })
    DirectionDTO directionToDirectionDTO(Direction entity);

    @Mappings({
            @Mapping(target = "directionName", source = "dto.directionName"),
            @Mapping(target = "directionCode", source = "dto.directionCode"),
            @Mapping(target = "faculty", source = "dto.faculty"),
            @Mapping(target = "teams", source = "dto.teams")
    })
    Direction directionDTOtoDirection(DirectionDTO dto);

    Set<DirectionDTO> setDirectionToDirectionDTO(Set<Direction> entity);
    Set<Direction> setDirectionDTOtoDirection(Set<DirectionDTO> entity);
}

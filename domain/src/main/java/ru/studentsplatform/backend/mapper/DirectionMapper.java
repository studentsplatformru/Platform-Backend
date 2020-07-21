package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.DirectionDTO;
import ru.studentsplatform.backend.entities.model.Direction;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectionMapper {
    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "directionName", source = "entity.directionName"),
            @Mapping(target = "directionCode", source = "entity.directionCode"),
            @Mapping(target = "faculty", source = "entity.faculty")
    })
    DirectionDTO directionToDirectionDTO(Direction entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "directionName", source = "dto.directionName"),
            @Mapping(target = "directionCode", source = "dto.directionCode"),
            @Mapping(target = "faculty", source = "dto.faculty")
    })
    Direction directionDTOtoDirection(DirectionDTO dto);

    List<DirectionDTO> listDirectionToDirectionDTO(List<Direction> entity);
    List<Direction> listDirectionDTOtoDirection(List<DirectionDTO> dto);
}

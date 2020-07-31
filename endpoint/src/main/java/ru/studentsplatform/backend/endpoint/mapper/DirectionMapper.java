package ru.studentsplatform.backend.endpoint.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.university.DirectionDTO;
import ru.studentsplatform.backend.entities.model.university.Direction;

import java.util.List;

/**
 * Маппер для конвертации объекта сущности направления в DTO,
 * а также List'ов, содержащих сущности в List DTO и обратно.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 31.07.2020
 */
@Mapper(componentModel = "spring", uses = {TeamMapper.class})
public interface DirectionMapper {
    /**
     * Трансформация полей сущности направления в поля DTO.
     *
     * @param entity Сущность направления, на основе которой будет создан DTO
     * @return DTO направления.
     */
    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "direction", source = "entity.direction"),
            @Mapping(target = "directionCode", source = "entity.directionCode"),
            @Mapping(target = "facultyId", source = "entity.faculty.id"),
            @Mapping(target = "teams", source = "entity.teams")
    })
    DirectionDTO directionToDirectionDTO(Direction entity);

    /**
     * Трансформация полей объекта DTO направления в поля сущности направления.
     *
     * @param dto DTO направления, на основе которого будет создана сущность.
     * @return Объект сущности направления.
     */
    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "direction", source = "dto.direction"),
            @Mapping(target = "directionCode", source = "dto.directionCode"),
            @Mapping(target = "faculty.id", source = "dto.facultyId"),
            @Mapping(target = "teams.id", source = "dto.teams")

    })
    Direction directionDTOToDirection(DirectionDTO dto);

    /**
     * Конвертирует сразу несколько Сущностей в DTO.
     *
     * @param entity Список направлений.
     * @return Список DTO.
     */
    List<DirectionDTO> listDirectionToDirectionDTO(List<Direction> entity);

    /**
     * Конвертирует сразу несколько DTO в сущности направлений.
     *
     * @param dto Список DTO.
     * @return Список направлений
     */
    List<Direction> listDirectionDTOToDirection(List<DirectionDTO> dto);

}

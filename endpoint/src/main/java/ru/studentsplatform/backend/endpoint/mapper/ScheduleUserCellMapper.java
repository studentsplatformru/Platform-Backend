package ru.studentsplatform.backend.endpoint.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.schedule.ScheduleUserCellDTO;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleUserCell;

import java.util.List;

/**
 * Маппер для конвертации объекта сущности ячейки пользовательского расписания в DTO,
 * а также List'ов, содержащих сущности в List DTO и обратно.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 31.07.2020
 */
@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface ScheduleUserCellMapper {
    /**
     * Трансформация полей сущности ячейки пользовательского расписания в поля DTO.
     *
     * @param entity Сущность ячейки пользовательского расписания, на основе которой будет создан DTO
     * @return DTO ячейки пользовательского расписания.
     */
    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "userId", source = "entity.user.id"),
            @Mapping(target = "scheduleCellId", source = "entity.scheduleCell.id"),
            @Mapping(target = "universityRole", source = "entity.universityRole"),
            @Mapping(target = "tasks", source = "entity.tasks")

    })
    ScheduleUserCellDTO scheduleUserCellToScheduleUserCellDTO(ScheduleUserCell entity);

    /**
     * Трансформация полей объекта DTO ячейки пользовательского расписания
     * в поля сущности ячейки пользовательского расписания.
     * @param dto DTO ячейки пользовательского расписания, на основе которого будет создана сущность.
     * @return Объект сущности ячейки пользовательского расписания.
     */
    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "user.id", source = "dto.userId"),
            @Mapping(target = "scheduleCell.id", source = "dto.scheduleCellId"),
            @Mapping(target = "universityRole", source = "dto.universityRole"),
            @Mapping(target = "tasks", source = "dto.tasks")
    })
    ScheduleUserCell scheduleUserCellDTOToScheduleUserCell(ScheduleUserCellDTO dto);

    /**
     * Конвертирует сразу несколько Сущностей в DTO.
     *
     * @param entity Список ячеек пользовательского расписания.
     * @return Список DTO.
     */
    List<ScheduleUserCellDTO> listScheduleUserCellToScheduleUserCellDTO(List<ScheduleUserCell> entity);

    /**
     * Конвертирует сразу несколько DTO в сущности ячеек пользовательского расписания.
     *
     * @param dto Список DTO.
     * @return Список ячеек пользовательского расписания
     */
    List<ScheduleUserCell> listScheduleUserCellDTOToScheduleUserCell(List<ScheduleUserCellDTO> dto);

}

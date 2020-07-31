package ru.studentsplatform.backend.endpoint.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.university.DepartmentDTO;
import ru.studentsplatform.backend.entities.model.university.Department;

import java.util.List;

/**
 * Маппер для конвертации объекта сущности кафедры в DTO,
 * а также List'ов, содержащих сущности в List DTO и обратно.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 31.07.2020
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface DepartmentMapper {

    /**
     * Трансформация полей сущности кафедры в поля DTO.
     *
     * @param entity Сущность кафедры, на основе которой будет создан DTO
     * @return DTO кафедры.
     */
    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "department", source = "entity.department"),
            @Mapping(target = "facultyId", source = "entity.faculty.id"),
            @Mapping(target = "users", source = "entity.users")

    })
    DepartmentDTO departmentToDepartmentDTO(Department entity);

    /**
     * Трансформация полей объекта DTO кафедры в поля сущности кафедры.
     *
     * @param dto DTO кафедры, на основе которого будет создана сущность.
     * @return Объект сущности кафедры.
     */
    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "department", source = "dto.department"),
            @Mapping(target = "faculty.id", source = "dto.facultyId"),
            @Mapping(target = "users", source = "dto.users")

    })
    Department departmentDTOToDepartment(DepartmentDTO dto);

    /**
     * Конвертирует сразу несколько Сущностей в DTO.
     *
     * @param entity Список кафедр.
     * @return Список DTO.
     */
    List<DepartmentDTO> listDepartmentToDepartmentDTO(List<Department> entity);

    /**
     * Конвертирует сразу несколько DTO в сущности кафедр.
     *
     * @param dto Список DTO.
     * @return Список кафедр
     */
    List<Department> listDepartmentDTOToDepartment(List<DepartmentDTO> dto);

}

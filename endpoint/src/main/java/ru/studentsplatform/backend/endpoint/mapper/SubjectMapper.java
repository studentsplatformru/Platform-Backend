package ru.studentsplatform.backend.endpoint.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.domain.dto.university.SubjectDTO;
import ru.studentsplatform.backend.entities.model.university.Subject;

import java.util.List;

/**
 * Маппер для конвертации объекта сущности предмета для студента в DTO,
 * а также List'ов, содержащих сущности в List DTO и обратно.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru) 26.07.2020
 */
@Mapper(componentModel = "spring")
public interface SubjectMapper {
    /**
     * Трансформация полей сущности предмета в поля DTO.
     *
     * @param entity Сущность предмета, на основе которой будет создан DTO
     * @return DTO предмета.
     */
    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "subjectName", source = "entity.subjectName")

    })
    SubjectDTO subjectToSubjectDTO(Subject entity);

    /**
     * Трансформация полей объекта DTO предмета в поля сущности предмета.
     *
     * @param dto DTO предмета, на основе которого будет создана сущность.
     * @return Объект сущности предмета.
     */
    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "subjectName", source = "dto.subjectName")

    })
    Subject subjectDTOToSubject(SubjectDTO dto);

    /**
     * Конвертирует сразу несколько Сущностей в DTO.
     *
     * @param entity Список предметов.
     * @return Список DTO.
     */
    List<SubjectDTO> listSubjectToSubjectDTO(List<Subject> entity);

    /**
     * Конвертирует сразу несколько DTO в сущности предметов.
     *
     * @param dto Список DTO.
     * @return Список предметов
     */
    List<Subject> listSubjectDTOToSubject(List<SubjectDTO> dto);

}

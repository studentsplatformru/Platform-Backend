package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.DepartmentDTO;
import ru.studentsplatform.backend.entities.model.Department;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FacultyMapper.class})
public interface DepartmentMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "departmentName", source = "entity.departmentName"),
            @Mapping(target = "facultyId", source = "entity.faculty.id")

    })
    DepartmentDTO departmentToDepartmentDTO(Department entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "departmentName", source = "dto.departmentName"),
            @Mapping(target = "faculty.id", source = "dto.facultyId")

    })
    Department departmentDTOToDepartment(DepartmentDTO dto);

    List<DepartmentDTO> listDepartmentToDepartmentDTO(List<Department> entity);

    List<Department> listDepartmentDTOtoDepartment(List<DepartmentDTO> dto);

}

package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.MaterialDTO;
import ru.studentsplatform.backend.entities.model.Material;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LibraryMapper.class, SubjectMapper.class})
public interface MaterialMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "link", source = "entity.link"),
            @Mapping(target = "libraryId", source = "entity.library.id"),
            @Mapping(target = "subjectId", source = "entity.subject.id")
    })
    MaterialDTO materialToMaterialDTO(Material entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "link", source = "dto.link"),
            @Mapping(target = "library.id", source = "dto.libraryId"),
            @Mapping(target = "subject.id", source = "dto.subjectId")
    })
    Material materialDTOtoMaterial(MaterialDTO dto);

    List<MaterialDTO> listMaterialToMaterialDTO(List<Material> entity);
    List<Material> listMaterialDTOtoMaterial(List<MaterialDTO> dto);

}

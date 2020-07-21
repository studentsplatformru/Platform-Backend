package ru.studentsplatform.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.studentsplatform.backend.dto.LibraryDTO;
import ru.studentsplatform.backend.entities.model.Library;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UniversityMapper.class})
public interface LibraryMapper {

    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "createdBy", source = "entity.createdBy"),
            @Mapping(target = "modifiedBy", source = "entity.modifiedBy"),
            @Mapping(target = "university", source = "entity.university")
    })
    LibraryDTO libraryToLibraryDTO(Library entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "createdBy", source = "dto.createdBy"),
            @Mapping(target = "modifiedBy", source = "dto.modifiedBy"),
            @Mapping(target = "university", source = "dto.university")

    })
    Library libraryDTOToLibrary(LibraryDTO dto);

    List<LibraryDTO> listLibraryToLibraryDTO(List<Library> entity);

    List<Library> listLibraryDTOtoLibrary(List<LibraryDTO> dto);

}

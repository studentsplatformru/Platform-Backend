package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.LibraryDTO;
import ru.studentsplatform.backend.endpoint.crud.LibraryController;
import ru.studentsplatform.backend.entities.model.Library;
import ru.studentsplatform.backend.mapper.LibraryMapper;
import ru.studentsplatform.backend.service.crud.LibraryService;

import java.util.List;

@RestController
@RequestMapping(LibraryController.BASE_URL)
public class LibraryControllerImpl implements LibraryController {

    private final LibraryService libraryService;

    private final LibraryMapper mapper;

    public LibraryControllerImpl(LibraryService libraryService, LibraryMapper mapper) {
        this.libraryService = libraryService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<LibraryDTO> create(LibraryDTO newInstanceRequest) {
        Library library = mapper.libraryDTOToLibrary(newInstanceRequest);
        library = libraryService.create(library);
        return ResponseEntity.ok(mapper.libraryToLibraryDTO(library));
    }

    @Override
    public ResponseEntity<LibraryDTO> getById(Long id) {
        Library library = libraryService.getById(id);
        LibraryDTO libraryDTO = mapper.libraryToLibraryDTO(library);
        return ResponseEntity.ok(libraryDTO);
    }

    @Override
    public ResponseEntity<List<LibraryDTO>> getAll() {
        List<Library> libraryList = libraryService.getAll();
        List<LibraryDTO> libraryDTOList = mapper.listLibraryToLibraryDTO(libraryList);
        return ResponseEntity.ok(libraryDTOList);
    }

    @Override
    public ResponseEntity<LibraryDTO> update(LibraryDTO updatedInstanceRequest, Long id) {
        Library library = mapper.libraryDTOToLibrary(updatedInstanceRequest);
        library = libraryService.update(library, id);
        return ResponseEntity.ok(mapper.libraryToLibraryDTO(library));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(libraryService.delete(id));
    }
}

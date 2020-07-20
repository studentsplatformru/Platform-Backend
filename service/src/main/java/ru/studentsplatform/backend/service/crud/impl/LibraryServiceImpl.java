package ru.studentsplatform.backend.service.crud.impl;

import ru.studentsplatform.backend.entities.model.Library;
import ru.studentsplatform.backend.repository.LibraryRepository;
import ru.studentsplatform.backend.service.crud.LibraryService;

import java.util.List;
import java.util.NoSuchElementException;

public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;

    public LibraryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public Library create(Library newEntity) {
        return libraryRepository.saveAndFlush(newEntity);
    }

    @Override
    public Library getById(Long id) {
        return libraryRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Library> getAll() {
        return libraryRepository.findAll();
    }

    @Override
    public Library update(Library updatedEntity, Long id) {
        Library library = libraryRepository.findById(id).orElseThrow(NoSuchElementException::new);
        library.setMaterials(updatedEntity.getMaterials());
        library.setUniversity(updatedEntity.getUniversity());
        return libraryRepository.saveAndFlush(library);
    }

    @Override
    public boolean delete(Long id) {
        if (libraryRepository.findById(id).isEmpty()){
            return false;
        }
        libraryRepository.deleteById(id);
        return true;
    }
}

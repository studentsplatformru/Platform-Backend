package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.entities.model.Library;
import ru.studentsplatform.backend.repository.LibraryRepository;
import ru.studentsplatform.backend.service.crud.LibraryService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
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
        updatedEntity.setId(id);
        return libraryRepository.saveAndFlush(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            libraryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

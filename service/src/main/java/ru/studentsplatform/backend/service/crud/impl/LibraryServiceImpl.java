package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.entities.model.Library;
import ru.studentsplatform.backend.repository.LibraryRepository;
import ru.studentsplatform.backend.repository.UniversityRepository;
import ru.studentsplatform.backend.service.crud.LibraryService;
import ru.studentsplatform.backend.service.crud.UniversityService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final UniversityRepository universityRepository;

    public LibraryServiceImpl(LibraryRepository libraryRepository, UniversityRepository universityRepository) {
        this.libraryRepository = libraryRepository;
        this.universityRepository = universityRepository;
    }

    @Override
    public Library create(Library newEntity) {
        newEntity.setUniversity(universityRepository.findByUniversityName(newEntity.getUniversity().getUniversityName()));
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

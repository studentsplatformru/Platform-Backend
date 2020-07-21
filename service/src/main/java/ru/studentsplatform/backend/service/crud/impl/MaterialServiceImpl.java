package ru.studentsplatform.backend.service.crud.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.entities.model.Material;
import ru.studentsplatform.backend.repository.MaterialRepository;
import ru.studentsplatform.backend.service.crud.MaterialService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Material create(Material newEntity) {
        return materialRepository.saveAndFlush(newEntity);
    }

    @Override
    public Material getById(Long id) {
        return materialRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Material> getAll() {
        return materialRepository.findAll();
    }

    @Override
    public Material update(Material updatedEntity, Long id) {
        updatedEntity.setId(id);
        return materialRepository.saveAndFlush(updatedEntity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            materialRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

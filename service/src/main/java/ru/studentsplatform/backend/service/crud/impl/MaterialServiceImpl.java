package ru.studentsplatform.backend.service.crud.impl;

import ru.studentsplatform.backend.entities.model.Material;
import ru.studentsplatform.backend.repository.MaterialRepository;
import ru.studentsplatform.backend.service.crud.MaterialService;

import java.util.List;
import java.util.NoSuchElementException;

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
        Material material = materialRepository.findById(id).orElseThrow(NoSuchElementException::new);
        material.setLibrary(updatedEntity.getLibrary());
        material.setLink(updatedEntity.getLink());
        material.setSubject(updatedEntity.getSubject());
        return materialRepository.saveAndFlush(material);
    }

    @Override
    public boolean delete(Long id) {
        if (materialRepository.findById(id).isEmpty()){
            return false;
        }
        materialRepository.deleteById(id);
        return true;
    }
}

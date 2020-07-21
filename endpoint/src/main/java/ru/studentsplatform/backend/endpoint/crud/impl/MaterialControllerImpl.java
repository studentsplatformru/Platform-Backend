package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.MaterialDTO;
import ru.studentsplatform.backend.endpoint.crud.MaterialController;
import ru.studentsplatform.backend.entities.model.Material;
import ru.studentsplatform.backend.mapper.MaterialMapper;
import ru.studentsplatform.backend.service.crud.MaterialService;

import java.util.List;

@RestController
@RequestMapping(MaterialController.BASE_URL)
public class MaterialControllerImpl implements MaterialController {
    private final MaterialService materialService;

    private final MaterialMapper mapper;

    public MaterialControllerImpl(MaterialService materialService, MaterialMapper mapper) {
        this.materialService = materialService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<MaterialDTO> create(MaterialDTO newInstanceRequest) {
        Material material = mapper.materialDTOtoMaterial(newInstanceRequest);
        material = materialService.create(material);
        return ResponseEntity.ok(mapper.materialToMaterialDTO(material));
    }

    @Override
    public ResponseEntity<MaterialDTO> getById(Long id) {
        Material material = materialService.getById(id);
        MaterialDTO materialDTO = mapper.materialToMaterialDTO(material);
        return ResponseEntity.ok(materialDTO);
    }

    @Override
    public ResponseEntity<List<MaterialDTO>> getAll() {
        List<Material> materialList = materialService.getAll();
        List<MaterialDTO> materialDTOList = mapper.listMaterialToMaterialDTO(materialList);
        return ResponseEntity.ok(materialDTOList);
    }

    @Override
    public ResponseEntity<MaterialDTO> update(MaterialDTO updatedInstanceRequest, Long id) {
        Material material = mapper.materialDTOtoMaterial(updatedInstanceRequest);
        material = materialService.update(material, id);
        return ResponseEntity.ok(mapper.materialToMaterialDTO(material));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(materialService.delete(id));
    }
}

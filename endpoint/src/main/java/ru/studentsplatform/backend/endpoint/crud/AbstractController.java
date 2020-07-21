package ru.studentsplatform.backend.endpoint.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AbstractController<DTO> {
    String BASE_URL = "/api";

    @PostMapping("/create")
    ResponseEntity<DTO> create(@RequestBody DTO newInstanceRequest);

    @GetMapping("/{id}")
    ResponseEntity<DTO> getById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<DTO>> getAll();

    @PutMapping("/{id}")
    ResponseEntity<DTO> update(@RequestBody DTO updatedInstanceRequest, @PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> delete(@PathVariable Long id);
}

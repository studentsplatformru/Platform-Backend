package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.studentsplatform.backend.system.exception.core.Fault;

import java.util.List;

public interface AbstractCRUDController<DTO> {
	String BASE_URL = "/api";

	@PostMapping("/create")
	ResponseEntity<DTO> create(@RequestBody DTO dto);

	@GetMapping("/{id}")
	ResponseEntity<DTO> getById(@PathVariable Long id) throws Fault;

	@GetMapping
	ResponseEntity<List<DTO>> getAll();

	@PutMapping("/{id}")
	ResponseEntity<DTO> update(@RequestBody DTO dto, @PathVariable Long id);

	@DeleteMapping("/{id}")
	ResponseEntity<Boolean> delete(@PathVariable Long id);
}

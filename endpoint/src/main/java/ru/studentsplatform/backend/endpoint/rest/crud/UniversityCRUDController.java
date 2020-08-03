package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.studentsplatform.backend.domain.dto.university.UniversityDTO;

import java.util.List;

public interface UniversityCRUDController extends AbstractCRUDController<UniversityDTO> {
	String BASE_URL = AbstractCRUDController.BASE_URL + "/university";

	@Override
	ResponseEntity<UniversityDTO> create(@RequestBody UniversityDTO universityDTO);

	@Override
	ResponseEntity<UniversityDTO> getById(@PathVariable Long id);

	@Override
	ResponseEntity<List<UniversityDTO>> getAll();

	@Override
	ResponseEntity<UniversityDTO> update(@RequestBody UniversityDTO universityDTO, @PathVariable Long id);

	@Override
	ResponseEntity<Boolean> delete(@PathVariable Long id);
}

package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.studentsplatform.backend.domain.dto.university.TeamDTO;

import java.util.List;

public interface TeamController extends AbstractController<TeamDTO> {
    String BASE_URL = AbstractController.BASE_URL + "/team";

    @Override
    ResponseEntity<TeamDTO> create(@RequestBody TeamDTO teamDTO);

    @Override
    ResponseEntity<TeamDTO> getById(@PathVariable Long id);

    @Override
    ResponseEntity<List<TeamDTO>> getAll();

    @Override
    ResponseEntity<TeamDTO> update(@RequestBody TeamDTO teamDTO, @PathVariable Long id);

    @Override
    ResponseEntity<Boolean> delete(@PathVariable Long id);
}

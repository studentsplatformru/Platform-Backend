package ru.studentsplatform.backend.endpoint.rest.crud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.dto.university.TeamDTO;
import ru.studentsplatform.backend.endpoint.mapper.TeamMapper;
import ru.studentsplatform.backend.endpoint.rest.TeamController;
import ru.studentsplatform.backend.service.crud.impl.TeamServiceImpl;
import ru.studentsplatform.backend.system.annotation.Profiled;

import java.util.List;

@Profiled
@RestController
@RequestMapping(TeamController.BASE_URL)
public class TeamControllerImpl implements TeamController {
    private final TeamMapper teamMapper;

    private final TeamServiceImpl teamService;

    /**
     * Конструктор.
     * @param teamMapper маппер, преобразующий TeamDTO в сущность Team и наоборот.
     * @param teamService CRUD сервис Team.
     */
    public TeamControllerImpl(TeamMapper teamMapper,
                                 TeamServiceImpl teamService) {
        this.teamMapper = teamMapper;
        this.teamService = teamService;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<TeamDTO> create(TeamDTO dto) {
        var team = teamMapper.teamDTOToTeam(dto);
        team = teamService.create(team);
        var result = teamMapper.teamToTeamDTO(team);
        return ResponseEntity.ok(result);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<TeamDTO> getById(Long id) {
        return ResponseEntity.ok(teamMapper.teamToTeamDTO(teamService.getById(id)));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<List<TeamDTO>> getAll() {
        return ResponseEntity.ok(teamMapper.listTeamToTeamDTO(teamService.getAll()));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<TeamDTO> update(TeamDTO dto, Long id) {
        var entity = teamMapper.teamDTOToTeam(dto);
        return ResponseEntity.ok(teamMapper
                .teamToTeamDTO(teamService.update(entity, id)));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(teamService.delete(id));
    }
}

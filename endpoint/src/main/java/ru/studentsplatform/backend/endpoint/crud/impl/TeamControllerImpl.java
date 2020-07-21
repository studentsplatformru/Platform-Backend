package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.TeamDTO;
import ru.studentsplatform.backend.endpoint.crud.TeamController;
import ru.studentsplatform.backend.entities.model.Team;
import ru.studentsplatform.backend.mapper.TeamMapper;
import ru.studentsplatform.backend.service.crud.TeamService;

import java.util.List;

@RestController
@RequestMapping(TeamController.BASE_URL)
public class TeamControllerImpl implements TeamController {
    private final TeamService teamService;

    private final TeamMapper mapper;

    public TeamControllerImpl(TeamService teamService, TeamMapper mapper) {
        this.teamService = teamService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<TeamDTO> create(TeamDTO newInstanceRequest) {
        Team team = mapper.teamDTOtoTeam(newInstanceRequest);
        team = teamService.create(team);
        return ResponseEntity.ok(mapper.teamToTeamDTO(team));
    }

    @Override
    public ResponseEntity<TeamDTO> getById(Long id) {
        Team team = teamService.getById(id);
        TeamDTO teamDTO = mapper.teamToTeamDTO(team);
        return ResponseEntity.ok(teamDTO);
    }

    @Override
    public ResponseEntity<List<TeamDTO>> getAll() {
        List<Team> teamList = teamService.getAll();
        List<TeamDTO> teamDTOList = mapper.listTeamToTeamDTO(teamList);
        return ResponseEntity.ok(teamDTOList);
    }

    @Override
    public ResponseEntity<TeamDTO> update(TeamDTO updatedInstanceRequest, Long id) {
        Team team = mapper.teamDTOtoTeam(updatedInstanceRequest);
        team = teamService.update(team, id);
        return ResponseEntity.ok(mapper.teamToTeamDTO(team));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(teamService.delete(id));
    }
}

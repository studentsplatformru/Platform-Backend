package ru.studentsplatform.backend.endpoint.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.domain.dto.university.TeamDTO;
import ru.studentsplatform.backend.entities.model.university.Direction;
import ru.studentsplatform.backend.entities.model.university.Team;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TeamMapperTest {
    @Mock
    UserMapper userMapper = new UserMapperImpl();

    @InjectMocks
    TeamMapper mapper = new TeamMapperImpl();

    /**
     * Проверка того, что маппер может создать DTO на основе сущности
     */
    @Test
    void teamToTeamDTOTest(){
        Team entity = new Team();
        Direction direction = new Direction();
        direction.setId(1L);
        entity.setSemester(2L);
        entity.setDirection(direction);
        entity.setTeamName("test");
        TeamDTO dto = mapper.teamToTeamDTO(entity);
        assertEquals(dto.getDirectionId(), entity.getDirection().getId());
        assertEquals(dto.getSemester(), entity.getSemester());
        assertEquals(dto.getTeamName(), entity.getTeamName());
    }

    /**
     * Проверка того, что маппер может создать сущность на основе DTO
     */
    @Test
    void teamDTOToTeamTest(){
        TeamDTO dto = new TeamDTO();
        dto.setDirectionId(1L);
        dto.setSemester(2L);
        dto.setTeamName("test");
        Team entity = mapper.teamDTOToTeam(dto);
        assertEquals(dto.getDirectionId(), entity.getDirection().getId());
        assertEquals(dto.getSemester(), entity.getSemester());
        assertEquals(dto.getTeamName(), entity.getTeamName());
    }
    /**
     * Проверка того, что маппер способен создать лист DTO на основе листа сущностей
     */
    @Test
    void listTeamToTeamDTOTest(){
        List<Team> teamList = new LinkedList<>();
        Team testTeam = new Team();
        testTeam.setId(3L);
        teamList.add(testTeam);
        teamList.add(testTeam);
        List<TeamDTO> teamDTOS = mapper.listTeamToTeamDTO(teamList);
        assertEquals(3L, teamDTOS.get(0).getId());
        assertEquals(3L, teamDTOS.get(1).getId());
    }
    /**
     * Проверка того, что маппер способен создать лист сущностей на основе листа DTO
     */
    @Test
    void listTeamDTOtoTeamTest() {
        List<TeamDTO> teamDTOS = new LinkedList<>();
        TeamDTO testTeamDTO = new TeamDTO();
        testTeamDTO.setId(5L);
        teamDTOS.add(testTeamDTO);
        teamDTOS.add(testTeamDTO);
        List<Team> teams = mapper.listTeamDTOToTeam(teamDTOS);
        assertEquals(5L, teams.get(0).getId());
        assertEquals(5L, teams.get(1).getId());
    }

}

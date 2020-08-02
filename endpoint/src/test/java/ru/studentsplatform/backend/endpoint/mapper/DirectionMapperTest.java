package ru.studentsplatform.backend.endpoint.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.domain.dto.university.DirectionDTO;
import ru.studentsplatform.backend.entities.model.university.Direction;
import ru.studentsplatform.backend.entities.model.university.Faculty;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DirectionMapperTest {

    @Mock
    TeamMapper teamMapper;

    @InjectMocks
    DirectionMapper mapper = new DirectionMapperImpl();

    /**
     * Проверка того, что маппер может создать DTO на основе сущности
     */
    @Test
    void directionToDirectionDTOTest(){
        Direction direction = new Direction();
        Faculty faculty = new Faculty();
        faculty.setId(2L);
        direction.setId(1L);
        direction.setFaculty(faculty);
        direction.setCreatedBy("test");
        direction.setDirectionCode("2242");
        DirectionDTO dto = mapper.directionToDirectionDTO(direction);
        assertEquals(dto.getId(), direction.getId());
        assertEquals(dto.getFacultyId(), direction.getFaculty().getId());
        assertEquals(dto.getCreatedBy(), direction.getCreatedBy());
        assertEquals(dto.getDirectionCode(),direction.getDirectionCode());
    }

    /**
     * Проверка того, что маппер способен создать сущность на основе DTO
     */
    @Test
    void directionDTOToDirectionTest(){
        DirectionDTO dto = new DirectionDTO();
        dto.setId(1L);
        dto.setFacultyId(2L);
        dto.setDirectionCode("0000");
        dto.setDirection("test");
        Direction direction = mapper.directionDTOToDirection(dto);
        assertEquals(dto.getId(), direction.getId());
        assertEquals(dto.getFacultyId(), direction.getFaculty().getId());
        assertEquals(dto.getDirectionCode(), direction.getDirectionCode());
        assertEquals(dto.getDirection(), direction.getDirection());
    }

    /**
     * Проверка того, что маппер способен создать лист DTO на основе листа сущностей
     */
    @Test
    void listDirectionToDirectionDTOTest(){
        List<Direction> directionList = new LinkedList<>();
        Direction testDirection = new Direction();
        testDirection.setId(3L);
        directionList.add(testDirection);
        directionList.add(testDirection);
        List<DirectionDTO> directionDTOS = mapper.listDirectionToDirectionDTO(directionList);
        assertEquals(3L, directionDTOS.get(0).getId());
        assertEquals(3L, directionDTOS.get(1).getId());
    }

    /**
     * Проверка того, что маппер способен создать лист сущностей на основе листа DTO
     */
    @Test
    void listDirectionDTOtoDirectionTest() {
        List<DirectionDTO> directionDTOS = new LinkedList<>();
        DirectionDTO testDirectionDTO = new DirectionDTO();
        testDirectionDTO.setId(5L);
        directionDTOS.add(testDirectionDTO);
        directionDTOS.add(testDirectionDTO);
        List<Direction> directions = mapper.listDirectionDTOToDirection(directionDTOS);
        assertEquals(5L, directions.get(0).getId());
        assertEquals(5L, directions.get(1).getId());
    }

}

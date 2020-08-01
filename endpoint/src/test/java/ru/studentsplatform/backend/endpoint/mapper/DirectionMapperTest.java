package ru.studentsplatform.backend.endpoint.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.domain.dto.university.DirectionDTO;
import ru.studentsplatform.backend.entities.model.university.Direction;
import ru.studentsplatform.backend.entities.model.university.Faculty;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DirectionMapperTest {

    @Mock
    TeamMapper teamMapper;

    @InjectMocks
    DirectionMapper mapper = new DirectionMapperImpl();

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

}

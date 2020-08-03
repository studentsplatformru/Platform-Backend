package ru.studentsplatform.backend.endpoint.mapper;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.domain.dto.user.UserDTO;
import ru.studentsplatform.backend.entities.model.user.User;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({MockitoExtension.class})
class UserMapperTest {

	@Mock
	DisciplineMapper disciplineMapper;

	@InjectMocks
	UserMapper mapper = new UserMapperImpl();

	/**
	 * Проверка того, что маппер может создать DTO на основе сущности
	 */
	@Test
	void userToUserDTOTest() {
		User user = new User();
		user.setId(Long.valueOf(1));
		user.setCreatedBy("created_by");
		user.setModifiedBy("modified_by");
		user.setEmail("email");
		user.setPassword("passwrd");
		UserDTO dto = mapper.userToUserDTO(user);
		Assert.assertEquals(user.getId(), dto.getId());
		Assert.assertEquals(user.getModifiedBy(), dto.getModifiedBy());
		Assert.assertEquals(user.getEmail(), dto.getEmail());
		Assert.assertEquals(user.getPassword(), dto.getPassword());
	}

	/**
	 * Проверка того, что маппер способен создать сущность на основе DTO
	 */
	@Test
	void userDTOtoUserTest() {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(Long.valueOf(1));
		userDTO.setCreatedBy("created_by");
		userDTO.setModifiedBy("modified_by");
		userDTO.setEmail("email");
		userDTO.setPassword("passwrd");

		User entity = mapper.userDTOtoUser(userDTO);
		Assert.assertEquals(userDTO.getId(), entity.getId());
		Assert.assertEquals(userDTO.getModifiedBy(), entity.getModifiedBy());
		Assert.assertEquals(userDTO.getEmail(), entity.getEmail());
		Assert.assertEquals(userDTO.getPassword(), entity.getPassword());
	}

	/**
	 * Проверка того, что маппер способен создать лист DTO на основе листа сущностей
	 */
	@Test
	void listUserToUserDTOTest() {
		List<User> userList = new LinkedList<>();
		User testUser = new User();
		testUser.setId(3L);
		userList.add(testUser);
		userList.add(testUser);
		List<UserDTO> userDTOS = mapper.listUserToUserDTO(userList);
		assertEquals(3L, userDTOS.get(0).getId());
		assertEquals(3L, userDTOS.get(1).getId());
	}

	/**
	 * Проверка того, что маппер способен создать лист сущностей на основе листа DTO
	 */
	@Test
	void listUserDTOtoUserTest() {
		List<UserDTO> userDTOS = new LinkedList<>();
		UserDTO testUserDTO = new UserDTO();
		testUserDTO.setId(5L);
		userDTOS.add(testUserDTO);
		userDTOS.add(testUserDTO);
		List<User> users = mapper.listUserDTOtoUser(userDTOS);
		assertEquals(5L, users.get(0).getId());
		assertEquals(5L, users.get(1).getId());
	}
}
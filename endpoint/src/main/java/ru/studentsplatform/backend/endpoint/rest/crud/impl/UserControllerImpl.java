package ru.studentsplatform.backend.endpoint.rest.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.domain.dto.user.UserDTO;
import ru.studentsplatform.backend.endpoint.mapper.UserMapper;
import ru.studentsplatform.backend.endpoint.rest.crud.UserController;
import ru.studentsplatform.backend.service.crud.impl.UserServiceImpl;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.List;

@Profiled
@RestController
@RequestMapping(UserController.BASE_URL)
public class UserControllerImpl implements UserController {
	private final UserMapper userMapper;

	private final UserServiceImpl userService;

	/**
	 * Конструктор.
	 *
	 * @param userMapper  маппер, преобразующий UserDTO в сущность User и наоборот.
	 * @param userService CRUD сервис User.
	 */
	public UserControllerImpl(UserMapper userMapper, UserServiceImpl userService) {
		this.userMapper = userMapper;
		this.userService = userService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<UserDTO> create(UserDTO dto) {
		var user = userMapper.userDTOtoUser(dto);
		user = userService.create(user);
		var result = userMapper.userToUserDTO(user);
		return ResponseEntity.ok(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<UserDTO> getById(Long id) {
		return ResponseEntity.ok(userMapper.userToUserDTO(userService.getById(id)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<UserDTO>> getAll() {
		return ResponseEntity.ok(userMapper.listUserToUserDTO(userService.getAll()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<UserDTO> update(UserDTO dto, Long id) {
		var entity = userMapper.userDTOtoUser(dto);
		return ResponseEntity.ok(userMapper.userToUserDTO(userService.update(entity, id)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<Boolean> delete(Long id) {
		return ResponseEntity.ok(userService.delete(id));
	}
}

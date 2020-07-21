package ru.studentsplatform.backend.endpoint.crud.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studentsplatform.backend.dto.UserDTO;
import ru.studentsplatform.backend.endpoint.crud.UserController;
import ru.studentsplatform.backend.entities.model.User;
import ru.studentsplatform.backend.mapper.UserMapper;
import ru.studentsplatform.backend.service.crud.UserService;

import java.util.List;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserControllerImpl implements UserController {
    private final UserService userService;

    private final UserMapper mapper;

    public UserControllerImpl(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<UserDTO> create(UserDTO newInstanceRequest) {
        User user = mapper.userDTOtoUser(newInstanceRequest);
        user = userService.create(user);
        return ResponseEntity.ok(mapper.userToUserDTO(user));
    }

    @Override
    public ResponseEntity<UserDTO> getById(Long id) {
        User user = userService.getById(id);
        UserDTO userDTO = mapper.userToUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAll() {
        List<User> userList = userService.getAll();
        List<UserDTO> userDTOList = mapper.listUserToUserDTO(userList);
        return ResponseEntity.ok(userDTOList);
    }

    @Override
    public ResponseEntity<UserDTO> update(UserDTO updatedInstanceRequest, Long id) {
        User user = mapper.userDTOtoUser(updatedInstanceRequest);
        user = userService.update(user, id);
        return ResponseEntity.ok(mapper.userToUserDTO(user));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(userService.delete(id));
    }
}

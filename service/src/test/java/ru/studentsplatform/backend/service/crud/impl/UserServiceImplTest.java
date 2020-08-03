package ru.studentsplatform.backend.service.crud.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.studentsplatform.backend.domain.repository.UserRepository;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.service.crud.UserService;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserServiceImpl service;

	User user;

	@BeforeEach
	void setup() {
		user = new User();
		user.setId(1L);
	}

	/**
	 * Test for create method
	 *
	 * @see UserService#create(User)
	 */
	@Test
	void createTest() {
		doReturn(user).when(userRepository).save(user);
		Assert.assertEquals(user, service.create(user));
	}

	/**
	 * Test for getById method
	 *
	 * @see UserService#getById(Long)
	 */
	@Test
	void getByIdTest() {
		doReturn(Optional.of(user)).doThrow(BusinessException.class).when(userRepository).findById(user.getId());
		Assert.assertEquals(user, service.getById(user.getId()));
		Assert.assertThrows(BusinessException.class, () -> service.getById(user.getId()));
	}

	/**
	 * Test for getAll method
	 *
	 * @see UserService#getAll()
	 */
	@Test
	void getAllTest() {
		List<User> user = new LinkedList<User>();
		doReturn(user).when(userRepository).findAll();
		Assert.assertEquals(user, service.getAll());
	}

	/**
	 * Test for update method
	 *
	 * @see UserService#update(User, Long)
	 */
	@Test
	void updateTest() {
		User new_user = new User();
		doReturn(true, false).when(userRepository).existsById(1L);
		doReturn(user).when(userRepository).saveAndFlush(user);
		Assert.assertEquals(user, service.update(new_user, 1L));

		Assert.assertThrows(BusinessException.class, () -> service.update(new_user, 1L));
	}

	/**
	 * Test for delete method
	 *
	 * @see UserService#delete(Long)
	 */
	@Test
	void deleteTest() {
		doNothing().doThrow(EmptyResultDataAccessException.class).when(userRepository).deleteById(user.getId());
		Assert.assertEquals(true, service.delete(user.getId()));
		Assert.assertEquals(false, service.delete(user.getId()));
	}
}
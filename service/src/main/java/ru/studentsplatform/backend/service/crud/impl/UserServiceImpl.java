package ru.studentsplatform.backend.service.crud.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.studentsplatform.backend.domain.repository.UserRepository;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.service.crud.UserService;
import ru.studentsplatform.backend.system.exception.core.BusinessException;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.List;

@Profiled
@Transactional
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    private final UserRepository userRepository;

    /**
     * Конструктор.
     * @param userRepository репозиторий пользователь
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User create(User newEntity) {
         return userRepository.save(newEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
              new BusinessException(ServiceExceptionReason.USER_NOT_FOUND, id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User update(User updatedEntity, Long id) {
        if (!userRepository.existsById(id)) {
          throw new BusinessException(ServiceExceptionReason.USER_NOT_FOUND, id);
        }
        updatedEntity.setId(id);
        return userRepository.saveAndFlush(updatedEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Error occured: cannot delete non-existent user");
            return false;
        }
        return true;
    }
}

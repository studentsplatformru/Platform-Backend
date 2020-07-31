package ru.studentsplatform.backend.service.crud.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.repository.UserInfoRepository;
import ru.studentsplatform.backend.domain.repository.UserRepository;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.entities.model.user.UserInfo;
import ru.studentsplatform.backend.service.crud.UserInfoService;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.system.annotation.Profiled;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import java.util.List;

@Profiled
@Transactional
@Service
public class UserInfoServiceImpl implements UserInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    private final UserInfoRepository userInfoRepository;

    private final UserRepository userRepository;

    /**
     * Конструктор.
     * @param userInfoRepository Репозиторий информации о пользователе.
     * @param userRepository     Репозиторий пользователя.
     */
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository, UserRepository userRepository) {
        this.userInfoRepository = userInfoRepository;
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserInfo create(UserInfo userInfo) {
        if (!userRepository.existsById(userInfo.getUser().getId())) {
            throw new BusinessException(ServiceExceptionReason.USER_NOT_FOUND, userInfo.getUser().getId());
        }
        if (userInfoRepository.existsById(userInfo.getUser().getId())) {
            throw new BusinessException(ServiceExceptionReason.USER_INFO_ALREADY_EXISTS,
                    userInfo.getUser().getId());
        }
        User user = userRepository.findById(userInfo.getUser().getId()).get();
        userInfo.setUser(user);
        return userInfoRepository.save(userInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserInfo getById(Long id) {
        return userInfoRepository.findById(id).orElseThrow(() ->
                new BusinessException(ServiceExceptionReason.USER_INFO_NOT_FOUND, id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfo> getAll() {
        return userInfoRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserInfo update(UserInfo updatedEntity, Long id) {
        updatedEntity.setId(id);
        return userInfoRepository.saveAndFlush(updatedEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Long id) {
        try {
            userInfoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Error occured: cannot delete non-existent user info");
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean uploadImage(MultipartFile file, Long userInfoId) {
        if (file == null) {
            throw new BusinessException(ServiceExceptionReason.NULL_IMAGE_FILE);
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        var userInfo = this.getById(userInfoId);
        try {
            userInfo.setImg(file.getBytes());
            userInfo.setImgName(fileName);
            userInfo.setImgType(file.getContentType());
        } catch (Exception e) {
            LOGGER.error("Error occurred while setting user info image");
            return false;
        }

        userInfoRepository.saveAndFlush(userInfo);
        return true;
    }
}

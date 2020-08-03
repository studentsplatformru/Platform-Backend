package ru.studentsplatform.backend.service.crud.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.repository.UserInfoRepository;
import ru.studentsplatform.backend.domain.repository.UserRepository;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.entities.model.user.UserInfo;
import ru.studentsplatform.backend.service.crud.UserInfoService;
import ru.studentsplatform.backend.service.crud.impl.UserInfoServiceImpl;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserInfoServiceImplTest {
    private UserInfo userInfo;

    @Mock
    UserInfoRepository userInfoRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserInfoServiceImpl service;

    @BeforeEach
    void setup(){
        userInfo = new UserInfo();
        userInfo.setId(1L);
    }
    /**
     * Test for create method
     * @see UserInfoService#create(UserInfo)
     */
    @Test
    void createTest() {
        User user = new User();
        user.setId(1L);
        userInfo.setUser(user);
        doReturn(userInfo).when(userInfoRepository).save(userInfo);
        doReturn(false, true).when(userInfoRepository).existsById(userInfo.getUser().getId());
        doReturn(true, true, false).when(userRepository).existsById(userInfo.getUser().getId());
        doReturn(Optional.of(user)).when(userRepository).findById(user.getId());
        Assert.assertEquals(userInfo, service.create(userInfo));
        Assert.assertThrows(BusinessException.class, () -> service.create(userInfo));
        Assert.assertThrows(BusinessException.class, () -> service.create(userInfo));
    }

    /**
     * Test for getById method
     * @see UserInfoService#getById(Long)
     */
    @Test
    void getByIdTest() {
        doReturn(Optional.of(userInfo)).when(userInfoRepository).findById(userInfo.getId());
        doThrow(BusinessException.class).when(userInfoRepository).findById(2L);
        assertEquals(userInfo,service.getById(userInfo.getId()));
        assertThrows(BusinessException.class,() -> service.getById(2L));
    }

    /**
     * Test for getAll method
     * @see UserInfoService#getAll()
     */
    @Test
    void getAllTest() {
        List<UserInfo> list = new LinkedList<UserInfo>();
        doReturn(list).when(userInfoRepository).findAll();
        Assert.assertEquals(list, service.getAll());
    }

    /**
     * Test for update method
     * @see UserInfoService#update(UserInfo, Long)
     */
    @Test
    void updateTest() {
        UserInfo updated_userInfo = new UserInfo();
        User user = new User();
        userInfo.setUser(user);
        doReturn(user).when(userRepository).getOne(userInfo.getId());
        doReturn(true, false).when(userInfoRepository).existsById(userInfo.getId());
        doReturn(updated_userInfo).when(userInfoRepository).saveAndFlush(updated_userInfo);
        var result = service.update(updated_userInfo, userInfo.getId());
        Assert.assertEquals(updated_userInfo,result);
        Assert.assertEquals(user, result.getUser());
        Assert.assertEquals(userInfo.getId(), result.getId());
        Assert.assertThrows(BusinessException.class, () -> service.update(userInfo, userInfo.getId()));
    }

    /**
     * Test for delete method
     * @see UserInfoService#delete(Long)
     */
    @Test
    void deleteTest() {
        doNothing().when(userInfoRepository).deleteById(userInfo.getId());
        Assert.assertEquals(true, service.delete(userInfo.getId()));
        userInfo.setId(2L);
        doThrow(EmptyResultDataAccessException.class).when(userInfoRepository).deleteById(userInfo.getId());
        Assert.assertEquals(false, service.delete(userInfo.getId()));
    }

    /**
     * Test for uploadImage method
     * @see UserInfoService#uploadImage(MultipartFile, Long)
     */
    @Test
    void uploadImageTest() {
        Assert.assertThrows(BusinessException.class,() -> service.uploadImage(null,1L));
        MultipartFile file = mock(MultipartFile.class);
        try { doThrow(IOException.class).doReturn(new byte[]{1}).when(file).getBytes(); }
        catch (IOException e) { }
        doReturn(Optional.of(userInfo)).when(userInfoRepository).findById(userInfo.getId());
        doReturn("filename").when(file).getOriginalFilename();
        doReturn("").when(file).getContentType();
        Assert.assertEquals(false, service.uploadImage(file,userInfo.getId()));

        doReturn(userInfo).when(userInfoRepository).saveAndFlush(userInfo);
        Assert.assertEquals(true, service.uploadImage(file,userInfo.getId()));
    }
}
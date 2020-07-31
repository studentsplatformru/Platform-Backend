package ru.studentsplatform.backend.service.crud.impl;

import org.junit.Assert;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.multipart.MultipartFile;
import ru.studentsplatform.backend.domain.repository.UserInfoRepository;
import ru.studentsplatform.backend.domain.repository.UserRepository;
import ru.studentsplatform.backend.entities.model.schedule.ScheduleUserCell;
import ru.studentsplatform.backend.entities.model.university.Task;
import ru.studentsplatform.backend.entities.model.user.User;
import ru.studentsplatform.backend.entities.model.user.UserInfo;
import ru.studentsplatform.backend.service.crud.UserInfoService;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.service.exception.core.BusinessException;
import ru.studentsplatform.backend.service.exception.core.BusinessExceptionReason;

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
     * Проверка того, что возвращается переданный объект.
     * Проверка гененрации ошибки, когда пользователь не найден.
     * Проверка генерации ошибки, когда инфа о пользователе уже создана.
     */
    @Test
    void create() {
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
     * Проверка возвращения значения, если найдена запись
     * и исключения в противном случае.
     */
    @Test
    void getById() {
        doReturn(Optional.of(userInfo)).when(userInfoRepository).findById(userInfo.getId());
        doThrow(BusinessException.class).when(userInfoRepository).findById(2L);
        assertEquals(userInfo,service.getById(userInfo.getId()));
        assertThrows(BusinessException.class,() -> service.getById(2L));
    }

    /**
     * Проверка того, что результатом является вызов findAll.
     */
    @Test
    void getAll() {
        List<UserInfo> list = new LinkedList<>();
        doReturn(list).when(userInfoRepository).findAll();
        Assert.assertEquals(userInfoRepository.findAll(),service.getAll());
    }

    /**
     * Проверка того, что в результате работы вернется запись с обновленным id
     */
    @Test
    void update() {
        Long newId = 2L;
        userInfo.setId(2L);
        doReturn(userInfo).when(userInfoRepository).saveAndFlush(userInfo);
        Assert.assertEquals(userInfo,service.update(userInfo,newId));
    }

    /**
     * Проверка true если есть запись
     * false если нет записи
     */
    @Test
    void delete() {
        doNothing().when(userInfoRepository).deleteById(userInfo.getId());
        Assert.assertEquals(true, service.delete(userInfo.getId()));
        userInfo.setId(2L);
        doThrow(EmptyResultDataAccessException.class).when(userInfoRepository).deleteById(userInfo.getId());
        Assert.assertEquals(false, service.delete(userInfo.getId()));
    }

    /**
     * Проверки:
     * Если файл пуст, то возвращаем Business exception
     * Если прервалась запись, то возвращаем false
     * Если удачно выполнено, то true
     * @throws IOException
     */
    @Test
    void uploadImage() {
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
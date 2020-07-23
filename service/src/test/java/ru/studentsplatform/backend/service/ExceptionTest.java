package ru.studentsplatform.backend.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.service.exception.core.BusinessException;

import static ru.studentsplatform.backend.service.exception.ServiceExceptionReason.*;

/**
 * Тесты для {@link BusinessException} с использованием
 * enum {@link ServiceExceptionReason}.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (10.07.2020).
 */
@ExtendWith(MockitoExtension.class)
public class ExceptionTest {

    /**
     * Тест исключения в случае отсутствующей записи.
     */
    @Test
    public void test_NOTE_NOT_FOUND(){

        try {

            throw new BusinessException(NOTE_NOT_FOUND, "Exception for test");

        } catch (BusinessException e) {

            Assert.assertEquals(e.getCode(), "F001");

            Assert.assertTrue(
                    e.getMessage().contains("Exception for test"));

            Assert.assertEquals(
                    e.getReason(), NOTE_NOT_FOUND);

            Assert.assertTrue(
                    e.getStatus().is4xxClientError());

        }
    }

    /**
     * Тест исключения в случае ошибки сервера.
     */
    @Test
    public void test_UNEXPECTED_ERROR(){

        try {

            throw new BusinessException(UNEXPECTED_ERROR, "Exception for test");

        }catch (BusinessException e){

            Assert.assertEquals(e.getCode(), "UNEXPECTED_ERROR");

            Assert.assertFalse(
                    e.getMessage().contains("Exception for test"));

            Assert.assertEquals(
                    e.getReason(), UNEXPECTED_ERROR);

            Assert.assertTrue(
                    e.getStatus().is5xxServerError());

        }
    }

    /**
     * Тест исключения в случае отсутствующего комментария.
     */
    @Test
    public void test_COMMENT_NOT_FOUND(){

        try {

            throw new BusinessException(COMMENT_NOT_FOUND, "Exception for test");

        }catch (BusinessException e){

            Assert.assertEquals(e.getCode(), "F002");

            Assert.assertTrue(
                    e.getMessage().contains("Exception for test"));

            Assert.assertEquals(
                    e.getReason(), COMMENT_NOT_FOUND);

            Assert.assertTrue(
                    e.getStatus().is4xxClientError());

        }
    }
}

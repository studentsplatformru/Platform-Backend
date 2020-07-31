package ru.studentsplatform.backend.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.service.exception.core.BusinessException;

import static ru.studentsplatform.backend.service.exception.ServiceExceptionReason.COMMENT_NOT_FOUND;
import static ru.studentsplatform.backend.service.exception.ServiceExceptionReason.NOTE_NOT_FOUND;
import static ru.studentsplatform.backend.service.exception.ServiceExceptionReason.UNEXPECTED_ERROR;
import static ru.studentsplatform.backend.service.exception.ServiceExceptionReason.USER_NOT_FOUND;

/**
 * Тесты для {@link BusinessException} с использованием
 * enum {@link ServiceExceptionReason}.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (23.07.2020).
 */
public class ExceptionTest {

	/**
	 * Тест исключения в случае отсутствующей записи.
	 */
	@Test
	public void test_NOTE_NOT_FOUND() {

		try {

			throw new BusinessException(NOTE_NOT_FOUND, 1);

		} catch (BusinessException e) {

			Assert.assertEquals("F001", e.getCode());

			Assert.assertTrue(
					e.getMessage().contains("1"));

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
	public void test_UNEXPECTED_ERROR() {

		try {

			throw new BusinessException(UNEXPECTED_ERROR, "Exception for test");

		} catch (BusinessException e) {

			Assert.assertEquals("UNEXPECTED_ERROR", e.getCode());

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
	public void test_COMMENT_NOT_FOUND() {

		try {

			throw new BusinessException(COMMENT_NOT_FOUND, 1);

		} catch (BusinessException e) {

			Assert.assertEquals("F002", e.getCode());

			Assert.assertTrue(
					e.getMessage().contains("1"));

			Assert.assertEquals(
					e.getReason(), COMMENT_NOT_FOUND);

			Assert.assertTrue(
					e.getStatus().is4xxClientError());

		}
	}

	/**
	 * Тест исключения в случае отсутствующего пользователя.
	 */
	@Test
	public void test_USER_NOT_FOUND() {

		try {

			throw new BusinessException(USER_NOT_FOUND, 1);

		} catch (BusinessException e) {

			Assert.assertEquals("F003", e.getCode());

			Assert.assertTrue(
					e.getMessage().contains("1"));

			Assert.assertEquals(
					e.getReason(), USER_NOT_FOUND);

			Assert.assertTrue(
					e.getStatus().is4xxClientError());

		}
	}
}

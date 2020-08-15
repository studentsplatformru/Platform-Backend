package ru.studentsplatform.backend.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.studentsplatform.backend.service.exception.ServiceExceptionReason;
import ru.studentsplatform.backend.system.exception.core.BusinessException;

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

			throw new BusinessException(ServiceExceptionReason.NOTE_NOT_FOUND, 1);

		} catch (BusinessException e) {

			Assert.assertEquals("F001", e.getCode());

			Assert.assertTrue(
					e.getMessage().contains("1"));

			Assert.assertEquals(
					e.getReason(), ServiceExceptionReason.NOTE_NOT_FOUND);

			Assert.assertTrue(
					e.getStatus().is4xxClientError());

		}
	}

	/**
	 * Тест исключения в случае отсутствующего комментария.
	 */
	@Test
	public void test_COMMENT_NOT_FOUND() {

		try {

			throw new BusinessException(ServiceExceptionReason.COMMENT_NOT_FOUND, 1);

		} catch (BusinessException e) {

			Assert.assertEquals("F002", e.getCode());

			Assert.assertTrue(
					e.getMessage().contains("1"));

			Assert.assertEquals(
					e.getReason(), ServiceExceptionReason.COMMENT_NOT_FOUND);

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

			throw new BusinessException(ServiceExceptionReason.USER_NOT_FOUND, 1);

		} catch (BusinessException e) {

			Assert.assertEquals("F003", e.getCode());

			Assert.assertTrue(
					e.getMessage().contains("1"));

			Assert.assertEquals(
					e.getReason(), ServiceExceptionReason.USER_NOT_FOUND);

			Assert.assertTrue(
					e.getStatus().is4xxClientError());

		}
	}
}

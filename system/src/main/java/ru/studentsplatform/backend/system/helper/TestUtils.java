package ru.studentsplatform.backend.system.helper;

import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Класс хелпер для тестирования.
 *
 * @author Krylov Sergey (15.08.2020)
 */
public class TestUtils {
	private TestUtils() {
	}

	/**
	 * Метод, который позволяет проверить, что было выброшено нужное исключение.
	 *
	 * @param supplier Выполняемый метод.
	 * @param error    Ошибка.
	 * @param <T>      Тип
	 */
	public static <T> void checkException(Supplier<T> supplier, Exception error) {
		try {
			supplier.get();
			fail();
		} catch (Exception var3) {
			assertEquals(error.getClass(), var3.getClass());
			assertEquals(error.getMessage(), var3.getMessage());
		}
	}
}
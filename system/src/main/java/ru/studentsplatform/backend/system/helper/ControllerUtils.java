package ru.studentsplatform.backend.system.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.studentsplatform.backend.system.exception.core.BusinessException;
import ru.studentsplatform.backend.system.exception.core.CompositeBusinessException;
import ru.studentsplatform.backend.system.exception.core.Fault;

import java.util.function.Supplier;

/**
 * Класс хелпер для контроллеров.
 *
 * @author Krylov Sergey (15.08.2020)
 */
public class ControllerUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerUtils.class);

	private ControllerUtils() {
	}

	/**
	 * Обработка ошибок, обёртка BusinessException (при отсутствии возвращаемого объекта можно использовать Void).
	 *
	 * @param supplier Функциональный интерфейс
	 * @param <T>      Тип возвращаемого значения
	 * @return Значение, возвращаемое реализацией интерфейса
	 * @throws Fault Ошибка
	 */
	public static <T> T handleExceptions(Supplier<T> supplier) throws Fault {
		try {
			return supplier.get();
		} catch (BusinessException be) {
			LOGGER.error("Бизнес ошибка: {}", be.getMessage());
			throw new Fault(be, be.getStatus());
		} catch (CompositeBusinessException cbe) {
			LOGGER.error("Список бизнес ошибок: {}", cbe.getMessage());
			throw new Fault(cbe, null);
		} catch (Exception exception) {
			LOGGER.error("Непредвиденная ошибка: {} - {}", exception.getClass(), exception.getMessage());
			LOGGER.error("{}", exception.getStackTrace()[0].toString());
			throw new Fault(exception, null);
		}
	}
}

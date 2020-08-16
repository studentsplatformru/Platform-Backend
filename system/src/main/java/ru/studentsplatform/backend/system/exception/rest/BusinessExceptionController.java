package ru.studentsplatform.backend.system.exception.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.studentsplatform.backend.system.exception.core.Fault;
import ru.studentsplatform.backend.system.exception.domain.ExceptionDTO;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

/**
 * Контроллер для перехвата исключений.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (30.07.2020).
 */
@Profiled
@ControllerAdvice
public class BusinessExceptionController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Исключения для бизнес ошибок.
	 *
	 * @param fault Получает аргументом общее исключение.
	 * @return ExceptionDTO тело ответа пользователю.
	 */
	@ExceptionHandler(Fault.class)
	@ResponseBody
	public ExceptionDTO exception(Fault fault) {
		return new ExceptionDTO(fault.getLocalizedMessage(), fault.getStatus(), fault.getClass().getName());
	}

	/**
	 * Исключения для ошибки необрабатываемых контроллерами путей,
	 * либо отсутствием страницы.
	 *
	 * @param exception Получает аргументом внешнее исключение.
	 * @return ExceptionDTO тело ответа пользователю.
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ExceptionDTO pageNotFound(Exception exception) {
		logger.error(exception.getMessage());
		return new ExceptionDTO("Page not found.", HttpStatus.NOT_FOUND, exception.getClass().getName());
	}

	/**
	 * Исключения для серверной ошибки.
	 *
	 * @param exception Получает аргументом внешнее исключение.
	 * @return ExceptionDTO тело ответа пользователю.
	 */
	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ExceptionDTO serverException(Exception exception) {
		logger.error(exception.getMessage());
		exception.printStackTrace();
		return new ExceptionDTO("Ошибка.", HttpStatus.INTERNAL_SERVER_ERROR, exception.getClass().getName());
	}
}
package ru.studentsplatform.backend.system.exception.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.studentsplatform.backend.system.log.impl.LogHelperImpl;

/**
 * Контроллер для перехвата исключений.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (30.07.2020).
 */
@ControllerAdvice
public class BusinessExceptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogHelperImpl.class);

    /**
     * Исключения для бизнес ошибок.
     * @param exception Получает агрументом внешнее исключение
     * выброшенное в контроллере.
     * @return ExceptionDto тело ответа пользователю.
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ExceptionDto exception(BusinessException exception) {
        LOGGER.error(exception.getMessage());
        return new ExceptionDto(exception.getMessage(), exception.getStatus());
    }

    /**
     * Исключения для ошибки необробатываемых контрллером путей,
     * либо отсутствием страницы.
     * @param exception Получает агрументом внешнее исключение.
     * @return ExceptionDto тело ответа пользователю.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionDto exceptionNOTFOUND(Exception exception) {
        LOGGER.error(exception.getMessage());
        return new ExceptionDto("Упс, страница не найдена.", HttpStatus.NOT_FOUND);
    }

    /**
     * Исключения для серверной ошибки.
     * @param exception Получает агрументом внешнее исключение.
     * После возвращает пользовател в виде {@link ExceptionDto}
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionDto serverException(Exception exception){
        LOGGER.error(exception.getMessage());
        return new ExceptionDto("Что-то пошло не так.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package ru.studentsplatform.backend.system.exception.core;

import org.springframework.http.HttpStatus;
import ru.studentsplatform.backend.system.exception.BusinessExceptionReason;
import ru.studentsplatform.backend.system.exception.domain.BusinessExceptionData;

/**
 * Исключение для бизнес ошибки.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (10.07.2020).
 */
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 8144390758971119153L;

	private final BusinessExceptionData businessExceptionData;

	public BusinessException(BusinessExceptionReason reason, Object... parameters) {
		this.businessExceptionData = new BusinessExceptionData(reason, parameters);
	}

	public BusinessExceptionReason getReason() {
		return businessExceptionData.getReason();
	}

	public Object[] getParameters() {
		return businessExceptionData.getParameters();
	}

	public String getCode() {
		return businessExceptionData.getReason().getCode();
	}

	public HttpStatus getStatus() {
		return businessExceptionData.getReason().getStatus();
	}

	/**
	 * Проверяем есть ли параметры, если нет, то возвращаем базовый текст
	 * иначе вставляем параметры.
	 *
	 * @return текст ошибки
	 */
	@Override
	public String getMessage() {
		return businessExceptionData.getMessage();
	}
}

package ru.studentsplatform.backend.system.exception.domain;

import ru.studentsplatform.backend.system.exception.BusinessExceptionReason;

import java.util.Arrays;
import java.util.Objects;

/**
 * ДТО для передачи данных в исключениях.
 * <p>
 * Т.к создание исключения - дорогая операция, то для реализации множества исключений нужен этот дто.
 * </p>
 *
 * @author Krylov Sergey (15.08.2020)
 */
public class BusinessExceptionData {

	private final Object[] parameters;

	private final BusinessExceptionReason reason;

	/**
	 * @param parameters Параметры исключения.
	 * @param reason     Причина возникновения исключения.
	 */
	public BusinessExceptionData(BusinessExceptionReason reason, Object[] parameters) {
		this.parameters = parameters;
		this.reason = reason;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public BusinessExceptionReason getReason() {
		return reason;
	}

	public String getMessage() {
		return Objects.isNull(parameters)
				? reason.getMessageWithParams().getMessage()
				: String.format(reason.getMessageWithParams().getMessage(), parameters);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BusinessExceptionData that = (BusinessExceptionData) o;
		return Arrays.equals(parameters, that.parameters) &&
				Objects.equals(reason, that.reason);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(reason);
		result = 31 * result + Arrays.hashCode(parameters);
		return result;
	}
}

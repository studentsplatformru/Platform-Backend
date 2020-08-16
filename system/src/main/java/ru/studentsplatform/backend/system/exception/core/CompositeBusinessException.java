package ru.studentsplatform.backend.system.exception.core;

import ru.studentsplatform.backend.system.exception.domain.BusinessExceptionData;
import ru.studentsplatform.backend.system.helper.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Список исключения для бизнес ошибок.
 *
 * @author Krylov Sergey (15.08.2020)
 */
public class CompositeBusinessException extends RuntimeException {
	private final List<BusinessExceptionData> businessExceptionDataList;

	/**
	 * Конструктор.
	 *
	 * @param businessExceptionDataList Список исключений.
	 */
	public CompositeBusinessException(List<BusinessExceptionData> businessExceptionDataList) {
		if (CollectionUtils.empty(businessExceptionDataList)) {
			throw new IllegalArgumentException("Исключение не может быть инициализировано пустым списком.");
		}
		this.businessExceptionDataList = businessExceptionDataList;
	}

	public List<BusinessExceptionData> getBusinessExceptionDataList() {
		return businessExceptionDataList;
	}

	@Override
	public String getMessage() {
		var parentMessage = super.getMessage() != null ? super.getMessage() + "\n" : "";

		var messagesFromExceptions = businessExceptionDataList.stream()
				.map(BusinessExceptionData::getMessage)
				.collect(Collectors.joining("\n"));

		return parentMessage + messagesFromExceptions;
	}
}

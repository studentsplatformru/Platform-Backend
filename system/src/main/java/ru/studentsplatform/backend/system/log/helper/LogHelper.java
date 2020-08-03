package ru.studentsplatform.backend.system.log.helper;

/**
 * Интерфейс для описывания методов для получения логов.
 *
 * @author Krylov Sergey (25.07.2020)
 */
public interface LogHelper {
	/**
	 * Метод возвращающий n последних строк логов.
	 *
	 * @param lines Кол-во последних строк которые надо показать.
	 * @return Логи
	 */
	String getLogs(Long lines);
}

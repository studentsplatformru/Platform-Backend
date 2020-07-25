package ru.studentsplatform.backend.system.log;

/**
 * Интерфейс для описывания методов для получения логов.
 *
 * @author Krylov Sergey (25.07.2020)
 */
public interface LogHelper {
	byte[] getLogs(Long lines);
}

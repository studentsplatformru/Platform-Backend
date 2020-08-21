package ru.studentsplatform.backend.university.schedule.spbu.service;

/**
 * Сервис, содержащий утилитные методы, относящиеся к СПБГУ.
 */
public interface SpbuUtilService {

	/**
	 * Совершает определенное кол-во запросов к БД СПБГУ.
	 * @param iterations Количество выполняемых запросов
	 * @return			 Была ли выброшена ошибка в процессе выполнения
	 */
	boolean stressTest(int iterations);

}

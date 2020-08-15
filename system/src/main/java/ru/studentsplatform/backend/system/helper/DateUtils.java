package ru.studentsplatform.backend.system.helper;

import java.time.format.DateTimeFormatter;

/**
 * Класс хелпер для дат.
 *
 * @author Krylov Sergey (15.08.2020)
 */
public class DateUtils {
	private DateUtils() {
	}

	private static final DateTimeFormatter LOCAL_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	private static final DateTimeFormatter LOCAL_TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

}

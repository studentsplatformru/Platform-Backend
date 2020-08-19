package ru.studentsplatform.backend.system.helper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Класс хелпер для дат.
 *
 * @author Krylov Sergey (15.08.2020)
 */
public class DateUtils {
	private static final DateTimeFormatter LOCAL_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	private static final DateTimeFormatter LOCAL_TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
	private DateUtils() {
	}

	/**
	 * @return LocalDateTime текущее время в своём часовом поясе.
	 *
	 * @author Danila K (karnacevich5323537@gmail.com) (15.08.2020).
	 */
	public static LocalDateTime getLocalDate() {
		return LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
	}

	/**
	 * @param localDateTime значение даты в виде {@link LocalDateTime}
	 * @return Date текущую дату в своём часовом поясе.
	 */
	public static Date getDateFromLocalDateTime(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
}

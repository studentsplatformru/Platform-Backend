package ru.studentsplatform.backend.system.helper;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.Month;
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

	/**
	 * @param dayOfWeek значение дня недели в виде {@link DayOfWeek}
	 * @return перевод значения текущего дня недели.
	 */
	public static String translateWeekDay(DayOfWeek dayOfWeek) {
		switch (dayOfWeek) {
			case MONDAY:
				return "Понедельник";
			case TUESDAY:
				return "Вторник";
			case WEDNESDAY:
				return "Среда";
			case THURSDAY:
				return "Четверг";
			case FRIDAY:
				return "Пятница";
			case SATURDAY:
				return "Суббота";
			case SUNDAY:
				return "Воскресенье";
			default: return "";
		}
	}

	/**
	 * @param month значение месяца в виде {@link Month}
	 * @return перевод значения месяца.
	 */
	public static String translateMonth(Month month) {
		switch (month) {
			case JANUARY:
				return "Январь";
			case FEBRUARY:
				return "Февраль";
			case MARCH:
				return "Март";
			case APRIL:
				return "Апрель";
			case MAY:
				return "Май";
			case JUNE:
				return "Июнь";
			case JULY:
				return "Июль";
			case AUGUST:
				return "Август";
			case SEPTEMBER:
				return "Сентябрь";
			case OCTOBER:
				return "Октябрь";
			case NOVEMBER:
				return "Ноябрь";
			case DECEMBER:
				return "Декабрь";
			default: return "";
		}
	}
}

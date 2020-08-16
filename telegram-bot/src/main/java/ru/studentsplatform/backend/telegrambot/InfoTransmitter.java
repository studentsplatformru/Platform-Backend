package ru.studentsplatform.backend.telegrambot;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс отвечает за предоставление боту объекта расписания, основываясь на Telegram userId.
 */
@Service
public class InfoTransmitter {
//    private final UniversityResolverFactory factory;
//
//    public InfoTransmitter(UniversityResolverFactory factory) {
//        this.factory = factory;
//    }

	/**
	 * Логгер.
	 */
	private static final Logger LOGGER = LogManager.getLogger(InfoTransmitter.class);

	/**
	 * Достаёт расписание из базы данных, основываясь на Telegram userId.
	 *
	 * @param userId уникальный идентификатор пользователя Telegram
	 * @return список объектов расписания за день
	 */
	public List<String> getScheduleById(int userId) {
		//TODO залезаем в бд и достаём информацию по userId
		LOGGER.log(Level.INFO, "залезаем в бд и достаём информацию по userId");

		List<String> schedules = null; //TODO залезаем в другую бд и достаём расписание по информации
		LOGGER.log(Level.INFO, "залезаем в другую бд и достаём расписание по информации");


		if (schedules == null) {
			schedules = createWeekSchedule("Объект с информацией о пользователе");
		}
		return schedules;
	}

	/**
	 * Если расписания в базе данных не оказалось, создаёт это расписание на основании объекта информации
	 * о пользователе. Новое расписание записывает в базу данных.
	 *
	 * @param info объект, представляющий информацию о пользователе, для составления объекта расписания
	 * @return список объектов расписания за день
	 */
	private List<String> createWeekSchedule(String info) {
		ArrayList<String> schedule = new ArrayList<>();
		schedule.add("День 1");
		schedule.add("День 2");
		schedule.add("День 3");
		schedule.add("День 4");
		schedule.add("День 5");
		return schedule;
	}
}

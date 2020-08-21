package ru.studentsplatform.backend.system.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.studentsplatform.backend.system.helper.DateUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Обработчик задач с отложенным действием.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (15.08.2020).
 */
@Component
public class JobManager {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Таймер для выполнения задач.
     */
    private final Timer timer = new Timer("Timer");

    /**
     * Метод для обработки задачи с задержкой.
     *
     * @param job Задача для выполнения реализованная в {@link Runnable}.
     * @param delay Задержка задачи в миллисекундах.
     */
    public void handle(Runnable job, long delay) {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                job.run();

                logger.info("In " + DateUtils.getLocalDateTime() + " Task done");
            }
        };

        timer.schedule(timerTask, delay);
    }

    /**
     * Метод для обработки задачи в конкретное время.
     *
     * @param job Задача для выполнения реализованная в {@link Runnable}.
     * @param stringDate Дата для выполнения задачи в формате dd.MM.yyyy HH:mm:ss
     */
    public void handle(Runnable job, String stringDate) {

        LocalDateTime localDateTime =
                LocalDateTime.parse(stringDate,
                        DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

        this.handle(job, localDateTime);
    }

    /**
     * Метод для обработки задачи в конкретное время.
     *
     * @param job Задача для выполнения реализованная в {@link Runnable}.
     * @param localDateTime Дата для выполнения задачи в формате  {@link LocalDateTime}.
     */
    public void handle(Runnable job, LocalDateTime localDateTime) {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                job.run();

                logger.info("In " + DateUtils.getLocalDateTime() + " Task done");
            }
        };

        timer.schedule(timerTask, DateUtils.getDateFromLocalDateTime(localDateTime));
    }
}

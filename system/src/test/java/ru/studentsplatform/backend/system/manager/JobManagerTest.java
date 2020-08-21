package ru.studentsplatform.backend.system.manager;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.system.helper.DateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

import static ru.studentsplatform.backend.system.helper.DateUtils.getDateFromLocalDateTime;
import static ru.studentsplatform.backend.system.helper.DateUtils.getLocalDateTime;


/**
 * Тесты для {@link JobManager}. Для тестирования используются
 * нативные java классы для работы со временем.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (15.08.2020).
 */
@ExtendWith(MockitoExtension.class)
public class JobManagerTest {

    private JobManager jobManager;

    @BeforeEach
    void before() {
        jobManager = new JobManager();
    }

    /**
     * Тестирует класс на работоспособность задержки задачи.
     */
    @Test
    public void testDelay() throws InterruptedException {

        long expectedDelay = 1000L;

        AtomicLong time = new AtomicLong(getDateFromLocalDateTime(getLocalDateTime()).getTime());

        jobManager.handle(() -> {

            time.addAndGet(-getDateFromLocalDateTime(getLocalDateTime()).getTime());

        }, expectedDelay);

        Thread.sleep(1500L);

        System.out.println(Math.abs(time.get()));
        // не раньше времени задержки
        Assert.assertTrue(Math.abs(time.get()) >= expectedDelay);
    }

    /**
     * Тестирует класс на отправку задачи в фиксированное время.
     */
    @Test
    public void testDate() throws InterruptedException {

        int expectedDelay = 1000;


        LocalDateTime localDateTime = getLocalDateTime();

        AtomicLong time = new AtomicLong(DateUtils.getDateFromLocalDateTime(localDateTime).getTime());

        jobManager.handle(() -> {

            time.addAndGet(-getDateFromLocalDateTime(getLocalDateTime()).getTime());

        }, localDateTime.plusSeconds(1));

        Thread.sleep(1500L);

        System.out.println(Math.abs(time.get()));
        // не раньше времени задержки
        Assert.assertTrue(Math.abs(time.get()) >= expectedDelay);
    }

}

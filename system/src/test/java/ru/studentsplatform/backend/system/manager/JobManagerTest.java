package ru.studentsplatform.backend.system.manager;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

import static ru.studentsplatform.backend.system.helper.DateUtils.getDateFromLocalDateTime;
import static ru.studentsplatform.backend.system.helper.DateUtils.getLocalDate;


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

        AtomicLong time = new AtomicLong(getDateFromLocalDateTime(getLocalDate()).getTime());

        jobManager.handle(() -> {

            time.addAndGet(-getDateFromLocalDateTime(getLocalDate()).getTime());

        }, expectedDelay);

        Thread.sleep(1500L);

        System.out.println(Math.abs(time.get()));
        // не раньше времени задержки
        Assert.assertTrue(Math.abs(time.get()) >= expectedDelay);
        // не дольше времени задержки с погрешностью в 100 мл
        Assert.assertTrue(Math.abs(time.get()) < expectedDelay + 100);
    }

    /**
     * Тестирует класс на отправку задачи в фиксированное время.
     */
    @Test
    public void testDate() throws InterruptedException {

        int expectedDelay = 1000;

        Calendar calendar = Calendar.getInstance();

        AtomicLong time = new AtomicLong(calendar.getTime().getTime());

        calendar.add(Calendar.MILLISECOND, expectedDelay);

        jobManager.handle(() -> {

            time.addAndGet(-getDateFromLocalDateTime(getLocalDate()).getTime());

        }, calendar.getTime());

        Thread.sleep(1500L);

        System.out.println(Math.abs(time.get()));
        // не раньше времени задержки
        Assert.assertTrue(Math.abs(time.get()) >= expectedDelay);
        // не дольше времени задержки с погрешностью в 100 мл
        Assert.assertTrue(Math.abs(time.get()) < expectedDelay + 100);
    }

}

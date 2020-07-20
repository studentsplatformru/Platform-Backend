package ru.studentsplatform.backend.service.spbu_new;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.entities.Respondent;
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpbuScheduleResolver implements UniversityScheduleResolver {
    private final RequestSender requestSender;
    private final ScheduleUtils scheduleUtils;

    @Autowired
    public SpbuScheduleResolver(RequestSender requestSender, ScheduleUtils scheduleUtils) {
        this.scheduleUtils = scheduleUtils;
        this.requestSender = requestSender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DaySchedule> getSchedule(@NotNull Respondent respondent, LocalDate leftDay, LocalDate rightDay) {
        assert (leftDay.isBefore(rightDay));  // хорошо бы еще знать, что respondent.groupID корректный
        List<DaySchedule> res;
        try {
            res = scheduleUtils.parseJsonSchedule(
                            requestSender.getScheduleResponse(respondent.getGroupId(), leftDay, rightDay).toString());
        } catch (IllegalArgumentException e) {
            return null;
        } catch (JSONException e) {
            return null;
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DaySchedule getDaySchedule(@NotNull Respondent respondent, DayOfWeek dayOfWeek) {
        final LocalDate currentDay = respondent.getDate().with(dayOfWeek);
        final int interval = 7;
        List<DaySchedule> res = getSchedule(respondent, currentDay, currentDay.plusDays(interval));
        if (res == null) {
            return null;
        }
        return (res.isEmpty())
                ? new DaySchedule(dayOfWeek, respondent.getDate().with(dayOfWeek), new ArrayList<>()) : res.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DaySchedule> getSchedule(@NotNull Respondent respondent) {
        final LocalDate currentMonday = respondent.getDate().with(DayOfWeek.MONDAY);
        final int interval = 7;
        return getSchedule(respondent, currentMonday, currentMonday.plusDays(interval));
    }
}

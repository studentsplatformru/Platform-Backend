package ru.studentsplatform.backend.service.spbu_new;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;
import ru.studentsplatform.backend.service.entities.Schedule.Lesson;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Вспомогательные функции для работы с расписанием СПБГУ.
 */
@Service
public class ScheduleUtils {

//    private final String spbuDatePattern = "yyyy-MM-dd";
    private final String spbuDatePattern;

//    private final String spbuDateTimePattern = "yyyy-MM-dd'T'HH:mm:ss";
    private final String spbuDateTimePattern;

    public ScheduleUtils(@Value("${spbuDatePattern}") String spbuDatePattern,
                  @Value("${spbuDateTimePattern}") String spbuDateTimePattern) {
        this.spbuDatePattern = spbuDatePattern;
        this.spbuDateTimePattern = spbuDateTimePattern;
    }
    /**
     * Форматирует дату в соответствие с необходимым форматом запросов к API расписания СПБГУ.
     *
     * @param date дата, которую надо отформатировать
     * @return отформатированная дата
     */
    public String getFormattedDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(spbuDatePattern));
    }

    /**
     * Разбирает JSON на необходимые сущности.
     *
     * @param data данные, возвращаемые API расписания в формате JSON
     * @return полученный список сущностей
     * @throws IllegalArgumentException если data == null
     * @throws JSONException если не получилось распарсить JSON
     */
    public List<DaySchedule> parseJsonSchedule(String data) throws IllegalArgumentException, JSONException {
        if (data == null) {
            throw new IllegalArgumentException("data must be nonnull");
        }
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(spbuDateTimePattern);
        List<DaySchedule> result = new ArrayList<>();
        JSONObject toParse = new JSONObject(data);
        JSONArray days = (JSONArray) toParse.get("Days");
        for (int i = 0; i < days.length(); ++i) {
            JSONObject day = (JSONObject) days.get(i);
            String stringDate = day.get("Day").toString();
            LocalDate dayDate = LocalDate.parse(stringDate, formatter);
            DayOfWeek dayOfWeek = dayDate.getDayOfWeek();
            List<Lesson> lessons = new ArrayList<>();
            JSONArray dayStudyEvents = (JSONArray) day.get("DayStudyEvents");
            for (int j = 0; j < dayStudyEvents.length(); ++j) {
                JSONObject studyEvent = (JSONObject) dayStudyEvents.get(j);
                LocalTime lessonStartTime = LocalTime.parse(studyEvent.get("Start").toString(), formatter);
                LocalTime lessonEndTime = LocalTime.parse(studyEvent.get("End").toString(), formatter);
                String lessonDiscipline = studyEvent.get("Subject").toString();
                String lessonLocations = studyEvent.get("LocationsDisplayText").toString();
                String lessonTeachers = studyEvent.get("EducatorsDisplayText").toString();
                lessons.add(new Lesson(
                        lessonStartTime,
                        lessonEndTime,
                        lessonDiscipline,
                        lessonLocations,
                        lessonTeachers));
            }
            result.add(new DaySchedule(
                    dayOfWeek,
                    dayDate,
                    lessons
            ));
        }
        return result;
    }
}

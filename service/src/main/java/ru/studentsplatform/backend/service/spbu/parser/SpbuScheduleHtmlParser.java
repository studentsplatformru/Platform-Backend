package ru.studentsplatform.backend.service.spbu.parser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.studentsplatform.backend.service.HtmlDocumentBuilder;
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;
import ru.studentsplatform.backend.service.entities.Schedule.Lesson;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Класс реализует интерфейс {@link ScheduleHtmlParser} и предлагает парсинг расписания
 * конкретной страницы университета СПБГУ.
 */
public class SpbuScheduleHtmlParser implements ScheduleHtmlParser {

    /**
     * Логгер.
     */
    private static Logger log = LogManager.getLogger(SpbuScheduleHtmlParser.class);

    /**
     * Возвращает объект класса DaySchedule, представляющего расписание за конкрктный день.
     *
     * @param requestedDay день, расписание которого необъодимо получить
     * @param requestedUrl адрес html-страницы с расписанием за неделю университета СПБГУ
     * @return объект расписания за день
     */
    @Override
    public DaySchedule getDaySchedule(DayOfWeek requestedDay, String requestedUrl) {
        Element containerElement = getContainerElement(requestedUrl);
        Element dayElement = getDayElement(requestedDay, containerElement);

        String weekStartDate = getWeekStartDate(containerElement);

        return buildSchedule(weekStartDate, dayElement);
    }

    /**
     * Возвращает список объектов класса DaySchedule.
     *
     * @param requestedUrl адрес html-страницы с расписанием за неделю университета СПБГУ
     * @return список объектов, представляющих расписание за день
     */
    @Override
    public List<DaySchedule> getWeekSchedule(String requestedUrl) {
        List<DaySchedule> daySchedules = new ArrayList<>();

        Element containerElement = getContainerElement(requestedUrl);
        Elements daysElements = getDaysElements(containerElement);

        String weekStartDate = getWeekStartDate(containerElement);

        daysElements
                .stream()
                .skip(1)
                .forEach((dayElement) ->
                        daySchedules.add(buildSchedule(weekStartDate, dayElement))
                );

        return daySchedules;
    }

    /**
     * Получает элемент, хранящий всю необходимую информацию для парсинга расписания.
     *
     * @param requestedUrl конкретный адрес расписания за неделю на сайте Spbu
     * @return элемент страницы
     */
    private Element getContainerElement(String requestedUrl) {
        try {
            return HtmlDocumentBuilder.getHtmlDocument(requestedUrl)
                    .select("div[class=container]")
                    .get(1);
        } catch (IOException e) {
            log.log(Level.FATAL, e);
        }
        throw new IllegalArgumentException();
    }

    /**
     * Извлекает аттрибут из переданного элемента.
     *
     * @param containerElement элемент, хранящий информацию о дате
     * @return строка в формате: yyyy-MM-dd
     */
    private String getWeekStartDate(Element containerElement) {
        return containerElement
                .getElementById("week")
                .attr("data-weekmonday");
    }

    /**
     * Возвращает элемент расписания запрашиваемого дня.
     *
     * @param requestedDay enum базового класса
     * @param container    элемент, хранящий информацию о расписании за неделю
     * @return элемент, представляющий расписание за день
     */
    private Element getDayElement(DayOfWeek requestedDay, Element container) {
        Elements daysElements = getDaysElements(container);

        String day = requestedDay
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        for (Element dayElement : daysElements) {
            String title = getDayTitle(dayElement);
            if (title.equals(day)) {
                return dayElement;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Возвращает все элементы контейнера, представляющие расписание за день.
     *
     * @param container элемент, хранящий информацию о расписании за неделю
     * @return список элементов
     */
    private Elements getDaysElements(Element container) {
        return container
                .select("div[class=panel panel-default]");
    }

    /**
     * В зависимости от переданных параметров строит объект Schedule, представляющий расписание за день.
     *
     * @param weekStartDate строка, представляющая дату занятий в формате: yyyy-MM-dd
     * @param dayElement    элемент, на основе которого строится Schedule
     * @return объект расписания за день
     */
    private DaySchedule buildSchedule(String weekStartDate, Element dayElement) {
        String dayName = getDayTitle(dayElement);
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(dayName.toUpperCase());
        LocalDate actualDate = getActualDate(weekStartDate, dayOfWeek);

        Elements lessonElements = getLessonElements(dayElement);
        List<Lesson> lessons = buildLessons(lessonElements);

        return new DaySchedule(dayOfWeek, actualDate, lessons);
    }

    /**
     * Возвращает дату согласно переданным аргументам.
     *
     * @param weekStartDate дата начала недели
     * @param dayOfWeek     день недели
     * @return объект, представляющий конкретную дату на данной неделе
     */
    private LocalDate getActualDate(String weekStartDate, DayOfWeek dayOfWeek) {
        return LocalDate
                .parse(weekStartDate)
                .plusDays(dayOfWeek.getValue() - 1);
    }

    /**
     * Превращает список элементов в список объектов, представляющих отдельный урок.
     *
     * @param lessonElements список элементов, представляющих урок
     * @return сконцтруированный список Lesson
     */
    private List<Lesson> buildLessons(Elements lessonElements) {
        List<Lesson> lessons = new ArrayList<>();
        for (Element lesson : lessonElements) {
            String[] time = getTime(lesson).split("–");

            lessons.add(
                    new Lesson(
                            LocalTime.parse(time[0]),
                            LocalTime.parse(time[1]),
                            getDisciplineName(lesson),
                            getLocation(lesson),
                            getEducatorName(lesson)
                    ));
        }
        return lessons;
    }

    /**
     * Сужает поиск по странице от элемента, представляющего день расписания,
     * до списка Element, представляющего информацию о каждом предмете в этот день.
     *
     * @param dayElement элемент дня расписания
     * @return Elements предметов заданного дня
     */
    private Elements getLessonElements(Element dayElement) {
        return dayElement
                .select("li[class=common-list-item row]");
    }

    /**
     * Согласно заданной инструкции извлекает {@link String} из переданного элемента.
     *
     * @param dayElement элемент дня расписания
     * @return строка с временем занятия
     */
    private String getDayTitle(Element dayElement) {
        return dayElement
                .select("h4[class=panel-title]")
                .text()
                .split(",")[0];
    }

    /**
     * Согласно заданной инструкции извлекает {@link String} из переданного элемента.
     *
     * @param dayElement элемент дня расписания
     * @return строка с временем занятия
     */
    private String getTime(Element dayElement) {
        return dayElement
                .select("div[class=col-sm-2 studyevent-datetime]").text();
    }

    /**
     * Согласно заданной инструкции извлекает {@link String} из переданного элемента.
     *
     * @param dayElement элемент дня расписания
     * @return строка с названием дисциплины
     */
    private String getDisciplineName(Element dayElement) {
        return dayElement
                .select("div[class=col-sm-4 studyevent-subject]").text();
    }

    /**
     * Согласно заданной инструкции извлекает {@link String} из переданного элемента.
     *
     * @param dayElement элемент дня расписания
     * @return строка с местом проведения занятия
     */
    private String getLocation(Element dayElement) {
        return dayElement
                .select("div[class=col-sm-3 studyevent-locations]").text();
    }

    /**
     * Согласно заданной инструкции извлекает {@link String} из переданного элемента.
     *
     * @param dayElement элемент дня расписания
     * @return строка с именем преподавателя
     */
    private String getEducatorName(Element dayElement) {
        return dayElement
                .select("div[class=col-sm-3 studyevent-educators]").text();
    }
}

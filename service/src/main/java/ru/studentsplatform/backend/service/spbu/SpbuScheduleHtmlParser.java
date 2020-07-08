package ru.studentsplatform.backend.service.spbu;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;
import ru.studentsplatform.backend.service.entities.Schedule.Lesson;
import ru.studentsplatform.backend.service.ScheduleHtmlParser;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Класс парсит определённую html-страницу расписания университета СПБГУ.
 * Класс подразумевает работу с конкретными URL.
 * <p>
 * Класс использует библиотеку {@link Jsoup},
 * рекомендуются к ознакомлению классы {@link Element} и {@link Elements} этой библиотеки.
 * Для подключения к удалённому URL используются класс {@link Document}
 * </p>
 *
 * @author spaulqr
 */
@Service
public class SpbuScheduleHtmlParser implements ScheduleHtmlParser {
    /**
     * Логгер класса.
     */
    private static Logger log = LogManager.getLogger(SpbuScheduleHtmlParser.class);


    /**
     * Возвращает Schedule.
     *
     * @param requestedDay день, на основе которого строится {@link DaySchedule}
     * @param requestedUrl адрес целевой страницы
     * @return Schedule или
     * null, если requestedDay не найден на странице.
     */
    public DaySchedule getDaySchedule(DayOfWeek requestedDay, String requestedUrl) {
        Element panelGroupElement = getPanelGroupElement(requestedUrl);

        int index = getTitleIndex(panelGroupElement, requestedDay
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH));

        if (index == -1) {
            return null;
        }

        return buildSchedule(panelGroupElement, index);
    }

    /**
     * Возвращает порядковый номер, соответствующий дню недели, представленной на странице.
     * <p>
     * Например, если на странице присутствует 3 дня расписания:
     * Monday,
     * Wednesday,
     * Friday
     * - то индексы каждого дня расписания будут соотвественно:
     * 1,
     * 2,
     * 3
     * </p>
     *
     * @param requestedDay      день, индекс которого необходимо найти
     * @param panelGroupElement элемент, представляющий расписание за неделю
     * @return -1, если день на странице не найден (или передана произвольная строка).
     */
    private int getTitleIndex(Element panelGroupElement, String requestedDay) {
        Elements elements = getDayNameElements(panelGroupElement);
        for (int index = 0; index < elements.size(); index++) {
            if (elements
                    .get(index)
                    .text()
                    .startsWith(requestedDay)) {
                return index;
            }
        }

        return -1;
    }

    /**
     * Возвращает Elements, содержащий названия
     * дней, представленных на странице.
     *
     * @param panelGroupElement элемент, представляющий расписание за неделю
     * @return элементы, содержащие название дней на панели
     */
    private Elements getDayNameElements(Element panelGroupElement) {
        return panelGroupElement
                .select("h4[class=panel-title]");
    }

    /**
     * Строит объект Schedule, исходя из передаваемого значения index.
     *
     * @param panelGroupElement элемент, представляющий расписание за неделю
     * @param index             порядковый номер дня на странице
     * @return сконфигурированный объект Schedule
     */
    private DaySchedule buildSchedule(Element panelGroupElement, int index) {
        Elements elements = getLessonsElements(panelGroupElement, index);
        String dayNameString = getDayNameElements(panelGroupElement)
                .get(index)
                .text()
                .toUpperCase();

        DayOfWeek title = DayOfWeek
                .valueOf(dayNameString.substring(0, dayNameString.indexOf(','))); //TODO подумать здесь

        List<Lesson> lessons = new ArrayList<>();

        for (Element element : elements) {
            lessons.add(new Lesson(
                    getTime(element),
                    getDisciplineName(element),
                    getLocation(element),
                    getEducatorName(element)
            ));
        }

        return new DaySchedule(title,
                lessons);
    }

    /**
     * Сужает поиск по странице от Element, представляющего день расписания,
     * до списка Element, представляющего информацию
     * о каждом предмете в этот день.
     *
     * @param panelGroupElement элемент, представляющий расписание за неделю
     * @param index             пробрасывается для вызова другого метода
     * @return Elements предметов заданного дня
     */
    private Elements getLessonsElements(Element panelGroupElement, int index) {
        return getDayElement(panelGroupElement, index)
                .select("li[class=common-list-item row]");
    }

    /**
     * Сужает поиск по странице от Element, представляющего всю неделю расписания,
     * до Element, представляющего конкретный день.
     *
     * @param panelGroupElement элемент, представляющий расписание за неделю
     * @param index             порядковый номер дня расписания
     * @return Element конкретного дня расписания
     */
    private Element getDayElement(Element panelGroupElement, int index) {
        return panelGroupElement
                .select("div[class=panel panel-default]").get(index);
    }

    /**
     * Сужает поиск по странице до панели с расписанием за неделю.
     *
     * @param requestedUrl адрес целевой страницы
     * @return элемент страницы, представляющий расписание за неделю
     */
    private Element getPanelGroupElement(String requestedUrl) {
        try {
            return getHtmlDocument(requestedUrl)
                    .select("div[class=panel-group]")
                    .first();
        } catch (IOException e) {
            log.log(Level.FATAL, e);
        }
        return null;
    }

    /**
     * Создаёт {@link Document} из html-страницы, используя Jsoup.connect.
     *
     * @param requestedUrl адрес целевой страницы
     * @return объект запрашиваемой html-страницы
     */
    private Document getHtmlDocument(String requestedUrl) throws IOException {
        return Jsoup.connect(requestedUrl).get();
    }

    /**
     * Согласно заданной инструкции извлекает {@link String} из переданного
     * Element}.
     *
     * @param element Element может содержать несколько тегов с указанным
     *                аттрибутом. Тогда метод вернёт соединённую
     *                через пробел строку с контентом, извлечённым
     *                из каждого элемента.
     * @return строка с временем занятия
     */
    private String getTime(Element element) {
        return element
                .select("div[class=col-sm-2 studyevent-datetime]").text();
    }

    /**
     * Согласно заданной инструкции извлекает {@link String} из переданного
     * Element.
     *
     * @param element Element может содержать несколько тегов с указанным
     *                аттрибутом. Тогда метод вернёт соединённую
     *                через пробел строку с контентом, извлечённым
     *                из каждого элемента.
     * @return строка с названием дисциплины
     */
    private String getDisciplineName(Element element) {
        return element
                .select("div[class=col-sm-4 studyevent-subject]").text();
    }

    /**
     * Согласно заданной инструкции извлекает {@link String} из переданного
     * Element.
     *
     * @param element Element может содержать несколько тегов с указанным
     *                аттрибутом. Тогда метод вернёт соединённую
     *                через пробел строку с контентом, извлечённым
     *                из каждого элемента.
     * @return строка с местом проведения занятия
     */
    private String getLocation(Element element) {
        return element
                .select("div[class=col-sm-3 studyevent-locations]").text();
    }

    /**
     * Согласно заданной инструкции извлекает {@link String} из переданного
     * Element.
     *
     * @param element Element может содержать несколько тегов с указанным
     *                аттрибутом. Тогда метод вернёт соединённую
     *                через пробел строку с контентом, извлечённым
     *                из каждого элемента.
     * @return строка с именем преподавателя
     */
    private String getEducatorName(Element element) {
        return element
                .select("div[class=col-sm-3 studyevent-educators]").text();
    }
}
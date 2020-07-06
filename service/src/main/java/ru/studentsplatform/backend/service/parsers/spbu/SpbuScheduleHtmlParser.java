package ru.studentsplatform.backend.service.parsers.spbu;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.parsers.ScheduleParser;
import ru.studentsplatform.backend.service.parsers.entities.Schedule;

import java.io.IOException;

/**
 * Класс парсит определённую html-страницу университета СПБГУ.
 * Класс подразумевает работу с конкретными URL.
 * <p>
 * Класс использует библиотеку {@link Jsoup},
 * рекомендуются к ознакомлению классы {@link Element} и {@link Elements} этой библиотеки.
 *
 * @author spaulqr
 */
@Service
public class SpbuScheduleHtmlParser implements ScheduleParser {
    private Connection connection;
    private Document document;

    /**
     * Устанавливает соединение со страницей
     *
     * @param requestedUrl запрашиваемый URL
     */
    public void setConnection(String requestedUrl) {
        this.connection = Jsoup.connect(requestedUrl);
    }

    /**
     * Возвращает {@code Schedule}.
     *
     * @param requestedDay день, на основе которого строится {@link Schedule}
     * @return {@code Schedule} или
     * {@code null}, если requestedDay не найден на странице.
     */
    public Schedule getDailySchedule(String requestedDay) {
        int index = getTitleIndex(requestedDay);

        if (index == -1) {
            return null;
        }

        return buildSchedule(index);
    }

    /**
     * Возвращает {@code int} порядковый номер, соответствующий дню недели, представленной на странице.
     * <p>
     * Например, если на странице присутствует 3 дня расписания:
     * Monday,
     * Wednesday,
     * Friday
     * - то индексы каждого дня расписания будут соотвественно:
     * 1,
     * 2,
     * 3
     *
     * @param requestedDay день, индекс которого необходимо найти
     * @return -1, если день на странице не найден (или передана произвольная строка).
     */
    public int getTitleIndex(String requestedDay) {
        Elements elements = getDayNameElements();
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
     * Возвращает {@code Elements}, содержащий названия
     * дней, представленных на странице.
     *
     * @return {@code Elements}
     */
    private Elements getDayNameElements() {
        return getPanelGroupElement()
                .select("h4[class=panel-title]");
    }

    /**
     * Строит объект {@code Schedule}, исходя из передаваемого значения {@code index}
     *
     * @param index порядковый номер дня на странице
     * @return сконфигурированный объект {@code Schedule}
     */
    private Schedule buildSchedule(int index) {
        Elements elements = getLessonsElements(index);
        int length = elements.size();
        String title = getDayNameElements()
                .get(index)
                .text();
        String[] times = new String[length];
        String[] disciplines = new String[length];
        String[] locations = new String[length];
        String[] educators = new String[length];

        for (int i = 0; i < elements.size(); i++) {
            times[i] = getTime(elements.get(i));
            disciplines[i] = getDisciplineName(elements.get(i));
            locations[i] = getLocation(elements.get(i));
            educators[i] = getEducatorName(elements.get(i));
        }

        return new Schedule(title,
                times,
                disciplines,
                locations,
                educators);
    }

    /**
     * Сужает поиск по странице от {@code Element}, представляющего день расписания,
     * до списка {@code Element}, представляющего информацию
     * о каждом предмете в этот день.
     *
     * @param index пробрасывается для вызова другого метода
     * @return {@code Elements} предметов заданного дня
     */
    private Elements getLessonsElements(int index) {
        return getDayElement(index)
                .select("li[class=common-list-item row]");
    }

    /**
     * Сужает поиск по странице от {@code Element}, представляющего всю неделю расписания,
     * до {@code Element}, представляющего конкретный день.
     *
     * @param index порядковый номер дня расписания
     * @return {@code Element} конкретного дня расписания
     */
    private Element getDayElement(int index) {
        return getPanelGroupElement()
                .select("div[class=panel panel-default]").get(index);
    }

    /**
     * Сужает поиск по странице до панели с расписанием за неделю.
     *
     * @return {@code Element}, представляющий расписание за неделю
     */
    private Element getPanelGroupElement() {
        if (document == null) {
            getHtmlDocument();
        }

        return document
                .select("div[class=panel-group]")
                .first();
    }

    /**
     * Создаёт {@link Document} из html-страницы, используя {@code connection}
     */
    private void getHtmlDocument() {
        try {
            document = connection.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Согласно заданной инструкции извлекает {@link String} из переданного
     * {@code Element}.
     *
     * @param element {@code Element} может содержать несколько тегов с указанным
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
     * {@code Element}.
     *
     * @param element {@code Element} может содержать несколько тегов с указанным
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
     * {@code Element}.
     *
     * @param element {@code Element} может содержать несколько тегов с указанным
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
     * {@code Element}.
     *
     * @param element {@code Element} может содержать несколько тегов с указанным
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
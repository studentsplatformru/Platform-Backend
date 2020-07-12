package ru.studentsplatform.backend.service.spbu.finder;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Класс представляет из себя реализацию интерфейса ScheduleUrlFinder для
 * университета СПБГУ. Класс необходим для обнаружения страницы с
 * расписанием интересующей нас группы.
 */
@Service
public class SpbuScheduleUrlFinder implements ScheduleUrlFinder {

    @Value("${spring.parser.baseUrl}")
    private String baseUrl;
    private Document document;

    /**
     * Метод выполняет несколько шагов, включающих переход по вебстраницам, в поисках ссылки на расписание.
     *
     * @param studyName Название направления подготовки на английском.
     * @param groupName Полное имя группы.
     * @return Ссылка на расписание для конкретной группы.
     */
    public String findScheduleLink(String studyName, String groupName) {
/*        return findScheduleForCurrentGroup(HtmlDocumentBuilder.getHtmlDocument(
                findSchedulesOfDefiniteStudy(HtmlDocumentBuilder.getHtmlDocument(
                        findFieldOfStudy(HtmlDocumentBuilder.getHtmlDocument(
                                baseUrl), studyName)), groupName)), groupName);*/
        return null; //TODO тута нужно что-то делать, комплиятор жалуется
    }

    /**
     * Метод обнаруживает ссылку на страницу, соответствующую направлению подготовки.
     *
     * @param htmlDoc   документ веб-страницы.
     * @param studyName Название направления подготовки на английском.
     * @return Ссылка на страницу со списком форм обученя конкретного направления.
     */
    private String findFieldOfStudy(Document htmlDoc, String studyName) {
        Elements studyFields = htmlDoc.select("li[class='list-group-item']");
        for (Element fieldOfStudy : studyFields) {
            if (studyName.equals(fieldOfStudy.text())) {
                return baseUrl + fieldOfStudy.select("a").attr("href").substring(1);
            }
        }
        return null;
    }

    /**
     * Метод обнаруживает ссылку на форму обучения, соответствующую имени группы.
     *
     * @param htmlDoc   документ веб-страницы.
     * @param groupName Полное имя группы.
     * @return Ссылка на страницу со списком групп на конкретной форме обучения.
     */
    private String findSchedulesOfDefiniteStudy(Document htmlDoc, String groupName) {
        Element link = htmlDoc.select("a[title*='" + groupName + "']").get(0);
        return baseUrl + link.attr("href").substring(1);
    }

    /**
     * Метод обнаруживает ссылку на конкретное расписание, соответствующую имени группы,
     * и, после небольшого форматирования возвращает значение.
     *
     * @param htmlDoc   документ веб-страницы.
     * @param groupName Полное имя группы.
     * @return Ссылка на страницу с расписанием для конкретной группы.
     */
    private String findScheduleForCurrentGroup(Document htmlDoc, String groupName) {
        final int startOfScheduleUrlInParam = 23;
        String tempUrl = baseUrl + htmlDoc.getElementsContainingOwnText(groupName)
                .get(0)
                .parent()
                .attr("onclick")
                .substring(startOfScheduleUrlInParam);
        return tempUrl.substring(0, tempUrl.length() - 1);
    }
}

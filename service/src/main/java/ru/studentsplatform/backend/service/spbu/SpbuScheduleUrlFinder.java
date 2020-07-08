package ru.studentsplatform.backend.service.spbu;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.ScheduleFinder;
import ru.studentsplatform.backend.service.HtmlDocumentBuilder;

@Service
public class SpbuScheduleUrlFinder implements ScheduleFinder {

    @Value("${spring.parser.baseUrl}")
    private String baseUrl;
    private Document document;

    /**
     * Метод выполняет несколько шагов, включающих переход по вебстраницам, в поисках ссылки на расписание.
     *
     * @param studyName Название направления подготовки на английском.
     * @param groupName Полное имя группы.
     * @return Ссылка на расписание конкретной группы.
     */
    public String findScheduleLink(String studyName, String groupName) {
            document = HtmlDocumentBuilder.getHtmlDocument(baseUrl);
            document = HtmlDocumentBuilder.getHtmlDocument(findFieldOfStudy(studyName));
            document = HtmlDocumentBuilder.getHtmlDocument(findSchedulesOfDefiniteStudy(groupName));
            return findScheduleForCurrentGroup(groupName);
    }

    /**
     * Метод обнаруживает ссылку на страницу, соответствующую направлению подготовки, и присваивает её полю tempUrl.
     *
     * @param studyName Название направления подготовки на английском.
     */
    private String findFieldOfStudy(String studyName) {
        Elements studyFields = document.select("li[class='list-group-item']");
        for (Element fieldOfStudy : studyFields) {
            if (studyName.equals(fieldOfStudy.text())) {
                return baseUrl + fieldOfStudy.select("a").attr("href").substring(1);
            }
        }
        return null;
    }

    /**
     * Метод обнаруживает ссылку на форму обучения, соответствующую имени группы, и присваивает её полю tempUrl.
     *
     * @param groupName Полное имя группы.
     */
    private String findSchedulesOfDefiniteStudy(String groupName) {
        Element link = document.select("a[title*='" + groupName + "']").get(0);
        return baseUrl + link.attr("href").substring(1);
    }

    /**
     * Метод обнаруживает ссылку на конкретное расписание, соответствующую имени группы,
     * и, после небольшого форматирования, присваивает её полю resultUrl.
     *
     * @param groupName Полное имя группы.
     */
    private String findScheduleForCurrentGroup(String groupName) {
        final int startOfScheduleUrlInParam = 23;
        String tempUrl = baseUrl + document.getElementsContainingOwnText(groupName)
                .get(0)
                .parent()
                .attr("onclick")
                .substring(startOfScheduleUrlInParam);
        return tempUrl.substring(0, tempUrl.length() - 1);
    }
}

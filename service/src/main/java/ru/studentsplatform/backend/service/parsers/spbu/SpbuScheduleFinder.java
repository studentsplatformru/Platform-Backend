package ru.studentsplatform.backend.service.parsers.spbu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import ru.studentsplatform.backend.service.parsers.ScheduleFinder;

import java.io.IOException;
import java.util.Properties;

@Component
public class SpbuScheduleFinder implements ScheduleFinder {

    private String baseUrl;
    private String tempUrl;
    private String resultUrl;
    private Document document;

    /**
     * Метод выполняет несколько шагов, включающих переход по вебстраницам, в поисках ссылки на расписание.
     *
     * @param studyName Название направления подготовки на английском.
     * @param groupName Полное имя группы.
     * @return Ссылка на расписание конкретной группы.
     */
    public String findScheduleLink(String studyName, String groupName) {
        try {
            loadProperties();
            setUpDocument();

            findFieldOfStudy(studyName);
            setUpDocument();

            findSchedulesOfDefiniteStudy(groupName);
            setUpDocument();

            findScheduleForCurrentGroup(groupName);
            return resultUrl;
        } catch (Exception e) {
            return "URL not found!";
        }
    }

    /**
     * Присваивание значений полям baseUrl и tempUrl на основе значения поля baseUrl в файле config.yml.
     */
    private void loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        baseUrl = properties.getProperty("baseUrl");
        tempUrl = baseUrl;
    }

    /**
     * Метод, загружающий веб-страницу по значению поля tempUrl.
     */
    private void setUpDocument() {
        try {
            document = Jsoup.connect(tempUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод обнаруживает ссылку на страницу, соответствующую направлению подготовки, и присваивает её полю tempUrl.
     *
     * @param studyName Название направления подготовки на английском.
     */
    private void findFieldOfStudy(String studyName) {
        Elements studyFields = document.select("li[class='list-group-item']");
        for (Element fieldOfStudy : studyFields) {
            if (studyName.equals(fieldOfStudy.text())) {
                tempUrl = baseUrl + fieldOfStudy.select("a").attr("href").substring(1);
            }
        }
    }

    /**
     * Метод обнаруживает ссылку на форму обучения, соответствующую имени группы, и присваивает её полю tempUrl.
     *
     * @param groupName Полное имя группы.
     */
    private void findSchedulesOfDefiniteStudy(String groupName) {
        Element link = document.select("a[title*='" + groupName + "']").get(0);
        tempUrl = baseUrl + link.attr("href").substring(1);
    }

    /**
     * Метод обнаруживает ссылку на конкретное расписание, соответствующую имени группы,
     * и, после небольшого форматирования, присваивает её полю resultUrl.
     *
     * @param groupName Полное имя группы.
     */
    private void findScheduleForCurrentGroup(String groupName) {
        final int startOfScheduleUrlInParam = 23;
        tempUrl = baseUrl + document.getElementsContainingOwnText(groupName)
                .get(0)
                .parent()
                .attr("onclick")
                .substring(startOfScheduleUrlInParam);
        resultUrl = tempUrl.substring(0, tempUrl.length() - 1);
    }
}

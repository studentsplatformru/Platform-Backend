package ru.platformbackend.service.parsers.spbu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;


public class SpbuScheduleFinder {
    private String baseUrl = "https://timetable.spbu.ru/";
    private String tempUrl = baseUrl;
    private String resultUrl;
    private Document document;

    public String findScheduleLink(String studyName, String groupName) {
        setUpDocument();

        findFieldOfStudy(studyName);
        setUpDocument();

        findSchedulesOfDefiniteStudy(groupName);
        setUpDocument();

        findScheduleForCurrentGroup(groupName);
        return resultUrl;
    }

    private void setUpDocument() {
        try {
            document = Jsoup.connect(tempUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findFieldOfStudy(String studyName) {
        Elements studyFields = document.select("li[class='list-group-item']");
        for (Element fieldOfStudy : studyFields) {
            if (studyName.equals(fieldOfStudy.text())) {
                tempUrl = baseUrl + fieldOfStudy.select("a").attr("href").substring(1);
            }
        }
    }

    private void findSchedulesOfDefiniteStudy(String groupName) {
        Element link = document.select("a[title*='" + groupName + "']").get(0);
        tempUrl = baseUrl + link.attr("href").substring(1);
    }


    private void findScheduleForCurrentGroup(String groupName) {
        tempUrl = baseUrl + document.getElementsContainingOwnText(groupName)
                .get(0)
                .parent()
                .attr("onclick")
                .substring(23);
        resultUrl = tempUrl.substring(0, tempUrl.length() - 1);
    }
}

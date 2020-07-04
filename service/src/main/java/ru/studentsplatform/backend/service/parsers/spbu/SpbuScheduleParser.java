package ru.studentsplatform.backend.service.parsers.spbu;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.parsers.ScheduleParser;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpbuScheduleParser implements ScheduleParser {
    private Connection connection;
    private Document document;

    public SpbuScheduleParser() {
    }

    public SpbuScheduleParser(String requestedUrl) {
        connection = Jsoup.connect(requestedUrl);
    }

    public String getDailySchedule(String currentDay) {
        int index = getTitleIndex(currentDay);

        if (index == -1) {
            return "Wrong request";
        }

        return getTitleElements().get(index).text() + ":\n"
                + getTimeElements().get(index).text() + "\n"
                + getDisciplineElements().get(index).text() + "\n"
                + getLocationElements().get(index).text() + "\n"
                + getEducatorElements().get(index).text() + "\n";
    }

    private int getTitleIndex(String currentDay) {
        List<String> list = elementsToTextList(getTitleElements());
        for (int index = 0; index < list.size(); index++) {
            if (list.get(index).startsWith(currentDay)) {
                return index;
            }
        }

        return -1;
    }

    private List<String> elementsToTextList(Elements elements) {
        return elements
                .stream()
                .map(Element::text)
                .collect(Collectors.toList());
    }

    private Element getPanelGroup() {
        if (document == null) {
            getHtmlDocument();
        }

        return document
                .select("div[class=panel-group]")
                .first();
    }

    private Elements getTitleElements() {
        return getPanelGroup()
                .select("h4[class=panel-title]");
    }

    private Elements getTimeElements() {
        return getPanelGroup()
                .select("div[class=col-sm-2 studyevent-datetime]");
    }

    private Elements getDisciplineElements() {
        return getPanelGroup()
                .select("div[class=col-sm-4 studyevent-subject]");
    }

    private Elements getLocationElements() {
        return getPanelGroup()
                .select("div[class=col-sm-3 studyevent-locations]");
    }

    private Elements getEducatorElements() {
        return getPanelGroup()
                .select("div[class=col-sm-3 studyevent-educators]");
    }

    private void getHtmlDocument() {
        try {
            document = connection.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

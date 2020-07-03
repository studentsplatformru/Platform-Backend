package ru.platformbackend.service.parsers.spbu;

public class ParserAndFinderTest {
    public static void main(String[] args) {
        SpbuScheduleParser parser = new SpbuScheduleParser(new SpbuScheduleFinder()
                        .findScheduleLink("Biology", "19.Б01-Б"));

    }
}

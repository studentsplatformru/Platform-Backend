package ru.platformbackend.service.parsers.spbu;

public class ParserAndFinderTest {
    public static void main(String[] args) {
        SpbuScheduleFinder finder = new SpbuScheduleFinder();
        long startTime = System.currentTimeMillis();
        String link = finder.findScheduleLink("Mahematics and Computer Science", "19.Б01-мкн");
        long secondTime = System.currentTimeMillis();
        System.out.println(link);

        SpbuScheduleParser parser = new SpbuScheduleParser(link);
        String friday = parser.getDailySchedule("Friday");
        long endTime = System.currentTimeMillis();

        System.out.println(secondTime - startTime);
        System.out.println(endTime - secondTime);
        System.out.println(friday);
    }
}
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.platformbackend.service.parsers.spbu.SpbuScheduleFinder;
import ru.platformbackend.service.parsers.spbu.SpbuScheduleParser;

public class SpbuParserAndFinderTest {
    private static SpbuScheduleFinder scheduleFinder;

    @BeforeAll
    public static void initAll(){
        scheduleFinder = new SpbuScheduleFinder();
    }

    @Test
    public void getLinkByGroupNameTest(){
        String Url = scheduleFinder.findScheduleLink("Biology", "19.Б01-Б");

        assertEquals(Url, "https://timetable.spbu.ru/BIOL/StudentGroupEvents/Primary/247986");
    }

    @Test
    public void wrongDataResponseTest(){
        String response = scheduleFinder.findScheduleLink("someString", "someString");
        assertEquals(response, "URL not found!");
    }

}

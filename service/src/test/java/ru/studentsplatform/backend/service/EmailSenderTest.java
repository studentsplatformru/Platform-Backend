package java.ru.studentsplatform.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import ru.studentsplatform.backend.service.EMailSender;

@SpringBootTest
public class EmailSenderTest {

    @Autowired
    private EMailSender eMailSender;

    @SpringBootApplication
    static class TestConfiguration{

    }

}


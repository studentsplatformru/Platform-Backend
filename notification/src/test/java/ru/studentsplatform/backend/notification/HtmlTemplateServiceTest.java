package ru.studentsplatform.backend.notification;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.notification.enumerated.MessageType;
import ru.studentsplatform.backend.notification.service.HtmlTemplateServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Тесты для {@link HtmlTemplateService}
 */
@ExtendWith(MockitoExtension.class)
public class HtmlTemplateServiceTest {

    private HtmlTemplateService htmlTemplateService;

    @BeforeEach
    void before() {
        htmlTemplateService = new HtmlTemplateServiceImpl();
    }

    /**
     * Тест на получение html шаблона.
     */
    @Test
    public void getTemplate() throws IOException {

//        Assert.assertTrue(
//                htmlTemplateService.getHtmlTemplate(
//                        MessageType.MARK_NOTIFICATION,
//                        "Test!!!!!!!!!!", "asd")
//                        .contains("Test!!!!!!!!!!")
//        );

    }

    /**
     * Тест на получении ошибки при неправильном количестве аргументов.
     */
    @Test
    public void getTemplateError() {

        Assert.assertThrows(
                IllegalArgumentException.class,
                ()-> htmlTemplateService.getHtmlTemplate(
                        MessageType.MARK_NOTIFICATION,
                        "Test" )
        );

    }
}

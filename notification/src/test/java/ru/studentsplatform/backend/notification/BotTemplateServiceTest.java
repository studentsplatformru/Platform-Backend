package ru.studentsplatform.backend.notification;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.notification.enumerated.MessageType;
import ru.studentsplatform.backend.notification.service.BotTemplateServiceImpl;

/**
 * Тесты для {@link BotTemplateService}
 *
 * @author Danila K (karnacevich5323537@gmail.com) (15.08.2020).
 */
@ExtendWith(MockitoExtension.class)
public class BotTemplateServiceTest {

    private BotTemplateService botTemplateService;

    @BeforeEach
    void before() {
        botTemplateService = new BotTemplateServiceImpl();
    }

    /**
     * Тест на получение текстового шаблона.
     */
    @Test
    public void getTemplate() {

        Assert.assertEquals(
                "У Вас новая оценка!\n Test : 5",
                botTemplateService.getBotTemplate(
                        MessageType.MARK_NOTIFICATION,
                        "Test", "5"));

    }

    /**
     * Тест на получении ошибки при неправильном количестве аргументов.
     */
    @Test
    public void getTemplateError() {

        Assert.assertThrows(
                IllegalArgumentException.class,
                ()-> botTemplateService.getBotTemplate(
                        MessageType.MARK_NOTIFICATION,
                        "Test" )
                );

    }
}

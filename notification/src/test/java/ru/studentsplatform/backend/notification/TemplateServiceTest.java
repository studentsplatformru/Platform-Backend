package ru.studentsplatform.backend.notification;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentsplatform.backend.entities.model.enums.NotificationType;
import ru.studentsplatform.backend.notification.enumerated.MessageType;
import ru.studentsplatform.backend.notification.service.TemplateServiceImpl;

/**
 * Тесты для {@link TemplateService}
 *
 * @author Danila K (karnacevich5323537@gmail.com) (15.08.2020).
 */
@ExtendWith(MockitoExtension.class)
public class TemplateServiceTest {

    private TemplateService templateService;

    @BeforeEach
    void before() {
        templateService = new TemplateServiceImpl();
    }

    /**
     * Тест на получении ошибки при неправильном количестве аргументов.
     */
    @Test
    public void getTemplateError() {

        Assert.assertThrows(
                IllegalArgumentException.class,
                ()-> templateService.getTemplate(
                        MessageType.MARK_NOTIFICATION,
                        NotificationType.Email,
                        "Test" )
        );

    }
}

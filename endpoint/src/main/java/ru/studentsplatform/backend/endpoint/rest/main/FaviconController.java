package ru.studentsplatform.backend.endpoint.rest.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.studentsplatform.backend.notification.NotifyService;
import ru.studentsplatform.backend.notification.enumerated.MessageType;

/**
 * Контроллер для выдачи иконки приложения.
 * Реализован в связи в ошибкой 404 при попытке загрузить иконку.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (08.08.2020).
 */
@Controller
class FaviconController {

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }
}

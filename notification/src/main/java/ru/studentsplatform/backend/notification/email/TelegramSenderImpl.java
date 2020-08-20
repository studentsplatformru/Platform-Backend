package ru.studentsplatform.backend.notification.email;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.studentsplatform.backend.notification.TelegramSender;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

/**
 * Реализация {@link TelegramSender}.
 *
 * Отправляет POST запрос на контроллер в телеграм-боте
 * с сообщением и id пользователя. Используется Json-обёртка
 * и {@link RestTemplate} для отправки.
 */
@Profiled
@Component
public class TelegramSenderImpl implements TelegramSender {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * URL-путь к серверу телеграм-бота.
     */
    @Value("${telegrambot.url}")
    private String url;

    /**
     * {@inheritDoc}
     */
    @Async
    @Override
    public void sendMessage(String userId, String text) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject object = new JSONObject();

        object.put("id", userId);
        object.put("text", text);

        HttpEntity<String> request = new HttpEntity<>(object.toString(), headers);

        ResponseEntity<String> responseEntityStr = restTemplate
                .postForEntity(url + "/bot/send", request, String.class);

        if (responseEntityStr.getStatusCode().is2xxSuccessful()) {
            logger.info("Telegram message to " + userId + " send.");
        } else {
            logger.error("Telegram message to " + userId + " not send.");
        }

    }
}

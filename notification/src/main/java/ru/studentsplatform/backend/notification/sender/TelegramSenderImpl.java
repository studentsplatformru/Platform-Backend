package ru.studentsplatform.backend.notification.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.studentsplatform.backend.domain.dto.telegram.TelegramMessageDTO;
import ru.studentsplatform.backend.notification.TelegramSender;
import ru.studentsplatform.backend.notification.util.MessageWrapper;
import ru.studentsplatform.backend.system.log.tree.annotation.Profiled;

import java.util.Collections;
import java.util.List;

/**
 * Реализация {@link TelegramSender}.
 *
 * Отправляет POST запрос на контроллер в телеграм-боте
 * с сообщением и id пользователя. Используется Json-обёртка
 * и {@link RestTemplate} для отправки.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (22.08.2020).
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
    @Override
    public void sendMessage(TelegramMessageDTO messageDTO) {
       this.sendMessage(Collections.singletonList(messageDTO));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Async
    public void sendMessage(List<TelegramMessageDTO> messages) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity(url + "/bot/send/messages", new MessageWrapper(messages), String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            logger.info("Telegram message send.");
        } else {
            logger.error("Telegram message not send.");
        }
    }
}

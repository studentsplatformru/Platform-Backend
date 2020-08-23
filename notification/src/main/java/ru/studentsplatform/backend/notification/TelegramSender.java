package ru.studentsplatform.backend.notification;

import ru.studentsplatform.backend.domain.dto.telegram.TelegramMessageDTO;

import java.util.List;

/**
 * Вспомогательный класс для отправки сообщения через Telegram.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (08.08.2020).
 */
public interface TelegramSender {

    /**
     * Метод для отправки сообщения через Telegram.
     *
     * @param messageDTO обёртка сообщения посылаемого в Telegram.
     */
    void sendMessage(TelegramMessageDTO messageDTO);

    /**
     * Метод для отправки сообщения через Telegram.
     *
     * @param messages обёртка сообщения посылаемого в Telegram.
     */
    void sendMessage(List<TelegramMessageDTO> messages);

}

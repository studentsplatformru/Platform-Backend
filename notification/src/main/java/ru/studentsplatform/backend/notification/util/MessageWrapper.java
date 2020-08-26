package ru.studentsplatform.backend.notification.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.studentsplatform.backend.domain.dto.telegram.TelegramMessageDTO;

import java.util.List;

/**
 * Класс-обёртка для отправки пакета сообщений Telegram боту.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (23.08.2020).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageWrapper {
    private List<TelegramMessageDTO> messages;
}

package ru.studentsplatform.backend.domain.dto.telegram;

import lombok.Data;

/**
 * Сущность для оправки сообщений Telegram боту.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (23.08.2020).
 */
@Data
public class TelegramMessageDTO {

    private Long id;
    private String text;

}

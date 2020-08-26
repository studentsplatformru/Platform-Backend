package ru.studentsplatform.backend.domain.dto.telegram;

import lombok.Data;

/**
 * Сущность для принятия пользователей с Telegram бота.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (23.08.2020).
 */
@Data
public class TelegramFollowerDTO {
    private Long id;
    private String teamName;
}

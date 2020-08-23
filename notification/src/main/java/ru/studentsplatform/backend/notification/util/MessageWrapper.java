package ru.studentsplatform.backend.notification.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.studentsplatform.backend.domain.dto.telegram.TelegramMessageDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageWrapper{
    private List<TelegramMessageDTO> messages;
}

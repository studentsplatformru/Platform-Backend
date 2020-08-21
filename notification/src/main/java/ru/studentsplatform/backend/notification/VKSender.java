package ru.studentsplatform.backend.notification;

/**
 * Вспомогательный класс для отправки сообщения через VK.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (08.08.2020).
 */
public interface VKSender {
    /**
     * @param userId адрес отправки сообщения.
     * @param message сообщение для отправки.
     *
     */
    void sendMessage(String userId, String message);

}

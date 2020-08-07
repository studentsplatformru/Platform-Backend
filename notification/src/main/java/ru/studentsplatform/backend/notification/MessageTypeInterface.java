package ru.studentsplatform.backend.notification;

/**
 * Интерфейс для описания типа сообщения.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (23.07.2020).
 */
public interface MessageTypeInterface {


    /**
     * @return путь к шаблону.
     */
    String getPath();

    /**
     * @return количество необходимых параметров.
     */
    int getParameterCount();
}

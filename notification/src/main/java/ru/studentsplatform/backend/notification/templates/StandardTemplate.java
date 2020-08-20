package ru.studentsplatform.backend.notification.templates;

/**
 * Расширение {@link AbstractTemplate} для обработки стандартного шаблона.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (20.08.2020).
 */
public class StandardTemplate extends AbstractTemplate {

    /**
     * @param path путь к html шаблону.
     * @param botPattern шаблон для короткого(бот) сообщения.
     */
    public StandardTemplate(String path, String botPattern) {
        super(path, botPattern);
    }

}

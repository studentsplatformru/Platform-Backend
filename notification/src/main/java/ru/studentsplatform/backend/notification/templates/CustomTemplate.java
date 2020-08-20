package ru.studentsplatform.backend.notification.templates;

import ru.studentsplatform.backend.notification.Template;

/**
 * Реализация {@link Template} для произвольного сообщения.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (20.08.2020).
 */
public class CustomTemplate extends AbstractTemplate{

    /**
     * @param path путь к html шаблону.
     * @param botPattern шаблон для короткого(бот) сообщения.
     */
    public CustomTemplate(String path, String botPattern) {
        super(path, botPattern);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHtmlTemplate(Object... args) {
        return (String) args[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBotTemplate(Object... args) {
        return this.getHtmlTemplate(args);
    }
}

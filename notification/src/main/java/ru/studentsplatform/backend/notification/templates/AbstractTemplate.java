package ru.studentsplatform.backend.notification.templates;

import ru.studentsplatform.backend.notification.Template;
import ru.studentsplatform.backend.notification.util.TemplateUtils;

/**
 * Реализация {@link Template}. Заложена базовая логика обработки
 * html и коротких(бот) сообщений.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (20.08.2020).
 */
public abstract class AbstractTemplate implements Template {

    private final String path;
    private final String botPattern;

    /**
     * @param path путь к html шаблону.
     * @param botPattern шаблон для короткого(бот) сообщения.
     */
    public AbstractTemplate(String path, String botPattern) {
        this.path = path;
        this.botPattern = botPattern;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHtmlTemplate(Object... args) {
        return TemplateUtils.getHtmlTemplateFromPath(path, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBotTemplate(Object... args) {
        return String.format(botPattern, args);
    }

    public String getPath() {
        return path;
    }

    public String getBotPattern() {
        return botPattern;
    }
}

package ru.studentsplatform.backend.notification.templates;

import ru.studentsplatform.backend.notification.Template;
import ru.studentsplatform.backend.notification.util.TemplateUtils;

public abstract class AbstractTemplate implements Template {

    private final String path;
    private final String botPattern;

    public AbstractTemplate(String path, String botPattern) {
        this.path = path;
        this.botPattern = botPattern;
    }

    @Override
    public String getHtmlTemplate(Object... args) {
        return TemplateUtils.getHtmlTemplateFromPath(path, args);
    }

    @Override
    public String getBotTemplate(Object... args) {
        return String.format(botPattern, args);
    }
}

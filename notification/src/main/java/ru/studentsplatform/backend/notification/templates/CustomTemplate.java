package ru.studentsplatform.backend.notification.templates;

public class CustomTemplate extends AbstractTemplate{

    public CustomTemplate(String path, String botPattern) {
        super(path, botPattern);
    }

    @Override
    public String getHtmlTemplate(Object... args) {
        return (String) args[0];
    }

    @Override
    public String getBotTemplate(Object... args) {
        return this.getHtmlTemplate(args);
    }
}

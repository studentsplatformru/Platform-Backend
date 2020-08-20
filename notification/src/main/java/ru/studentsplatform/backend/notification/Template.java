package ru.studentsplatform.backend.notification;

public interface Template {

    String getHtmlTemplate(Object... args);

    String getBotTemplate(Object... args);

}

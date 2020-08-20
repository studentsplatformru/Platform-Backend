package ru.studentsplatform.backend.notification.templates;

import ru.studentsplatform.backend.entities.model.spbu.SpbuEvent;
import ru.studentsplatform.backend.notification.Template;
import ru.studentsplatform.backend.notification.util.TemplateUtils;

import java.time.LocalDate;
import java.util.List;

import static ru.studentsplatform.backend.system.helper.DateUtils.translateMonth;
import static ru.studentsplatform.backend.system.helper.DateUtils.translateWeekDay;

/**
 * Реализация {@link Template} для обработки расписания за день.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (20.08.2020).
 */
public class EventsDayTemplate extends AbstractTemplate{

    /**
     * @param path путь к html шаблону.
     * @param botPattern шаблон для короткого(бот) сообщения.
     */
    public EventsDayTemplate(String path, String botPattern) {
        super(path, botPattern);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHtmlTemplate(Object... args) {
        List<SpbuEvent> events = (List<SpbuEvent>) args[0];

        if (events == null || events.size() == 0) {
            throw new IllegalArgumentException("Список с расписанием пустой!");
        }

        LocalDate date = events.get(0).getDate();

        String dateString = translateWeekDay(date.getDayOfWeek()) +
                " " + date.getDayOfMonth() + "-ое, " + translateMonth(date.getMonth());

        StringBuilder builder = new StringBuilder();

        for (SpbuEvent event: events){
            builder
                    .append("<li><h4>")
                    .append(event.getStartTime())
                    .append(" - ")
                    .append(event.getSubject())
                    .append("</h4></li>");
        }

        return TemplateUtils.getHtmlTemplateFromPath(
                this.getPath(),dateString , builder.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBotTemplate(Object... args) {
        List<SpbuEvent> events = (List<SpbuEvent>) args[0];

        if (events == null || events.size() == 0) {
            throw new IllegalArgumentException("Список с расписанием пустой!");
        }

        LocalDate date = events.get(0).getDate();
        StringBuilder builder = new StringBuilder();

        builder
                .append(translateWeekDay(date.getDayOfWeek()))
                .append(" ")
                .append(date.getDayOfMonth()).append("-ое, ")
                .append(translateMonth(date.getMonth()));
        builder.append("\n\n");

        for (SpbuEvent event: events){
            builder
                    .append(event.getStartTime())
                    .append(": ")
                    .append(event.getSubject())
                    .append("\n\n");
        }
        builder.append("Удачного дня!☺");

        return builder.toString();
    }
}

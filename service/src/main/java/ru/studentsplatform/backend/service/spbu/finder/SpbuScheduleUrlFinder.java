package ru.studentsplatform.backend.service.spbu.finder;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.service.HtmlDocumentBuilder;
import java.io.IOException;

/**
 * Класс представляет из себя реализацию интерфейса ScheduleUrlFinder для
 * университета СПБГУ. Класс необходим для обнаружения страницы с
 * расписанием интересующей нас группы.
 */
@Service
public class SpbuScheduleUrlFinder implements ScheduleUrlFinder {

    @Value("${spring.parser.baseUrl:https://timetable.spbu.ru/}")
    private String baseUrl;

    /**
     * Метод выполняет несколько шагов, включающих переход по вебстраницам, в поисках ссылки на расписание.
     *
     * @param studyName Название направления подготовки на английском.
     * @param groupName Полное имя группы.
     * @return Ссылка на расписание для конкретной группы.
     */
    public String findScheduleLink(String studyName, String groupName) throws IllegalArgumentException {
        Document document;
        try {
            document = HtmlDocumentBuilder.getHtmlDocument(baseUrl);
            document = findFieldOfStudy(document, studyName);
            document = findSchedulesOfDefiniteStudy(document, groupName);
            return findScheduleForCurrentGroup(document, groupName);
        } catch (IOException | NullPointerException e) {
                //ToDo: по идее можно не обрабатывать, но под вопросом!
        }
        throw new IllegalArgumentException();
    }

    /**
     * Метод обнаруживает страницу, соответствующую направлению подготовки.
     *
     * @param htmlDoc   документ веб-страницы.
     * @param studyName Название направления подготовки на английском.
     * @return Документ, соответствующий ссылке на страницу со списком форм обученя конкретного направления.
     */
    private Document findFieldOfStudy(Document htmlDoc, String studyName) throws IOException, NullPointerException {
        return HtmlDocumentBuilder.getHtmlDocument(
                baseUrl +
                        htmlDoc.getElementsContainingOwnText(studyName)
                        .first()
                        .attr("href")
                        .substring(1));
    }

    /**
     * Метод обнаруживает страницу формы обучения, соответствующую имени группы.
     *
     * @param htmlDoc   документ веб-страницы.
     * @param groupName Полное имя группы.
     * @return Документ, соответствующий ссылке со списком групп на конкретной форме обучения.
     */
    private Document findSchedulesOfDefiniteStudy(Document htmlDoc, String groupName) throws IOException, NullPointerException {
        Element link = htmlDoc.select("a[title*='" + groupName + "']").first();
        return HtmlDocumentBuilder.getHtmlDocument(baseUrl + link.attr("href").substring(1));
    }

    /**
     * Метод обнаруживает ссылку на конкретное расписание, соответствующую имени группы,
     * и, после небольшого форматирования возвращает значение.
     *
     * @param htmlDoc   документ веб-страницы.
     * @param groupName Полное имя группы.
     * @return Ссылка на страницу с расписанием для конкретной группы.
     */
    private String findScheduleForCurrentGroup(Document htmlDoc, String groupName) throws NullPointerException {
        final int startOfScheduleUrlInParam = 23;
        String tempUrl = baseUrl + htmlDoc.getElementsContainingOwnText(groupName)
                .first()
                .parent()
                .attr("onclick")
                .substring(startOfScheduleUrlInParam);
        return tempUrl.substring(0, tempUrl.length() - 1);
    }
}

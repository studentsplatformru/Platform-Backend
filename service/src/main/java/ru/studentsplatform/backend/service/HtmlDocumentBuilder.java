package ru.studentsplatform.backend.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

/**
 * Класс предоставляет удобный способ получить документ веб-страницы по её URL.
 */
public class HtmlDocumentBuilder {

    /**
     * Возвращает документ вебстраницы, находящейся по указанному в параметре URL.
     * @param url Адрес веб-страницы.
     * @return Докусени искомой веб-страницы.
     */
    public static Document getHtmlDocument(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

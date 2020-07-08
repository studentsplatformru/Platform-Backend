package ru.studentsplatform.backend.service.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class HtmlDocumentBuilder {

    public static Document getHtmlDocument(String url){
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

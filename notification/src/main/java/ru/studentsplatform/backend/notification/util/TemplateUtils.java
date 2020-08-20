package ru.studentsplatform.backend.notification.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.studentsplatform.backend.notification.TemplateService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Вспомогательный класс для работы с шаблонами.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (20.08.2020).
 */
public class TemplateUtils {

    private final static Logger logger = LoggerFactory.getLogger(TemplateUtils.class);


    /**
     * Костыльная обработка шаблона)
     * @param path путь к файлу.
     * @param args аргументы для вставки.
     * @return готовое для отправки сообщение.
     */
    public static String getHtmlTemplateFromPath(String path, Object... args){
        // обрабатывает и выдаёт сообщение для отправки через email
        try (Scanner scanner = new Scanner(Paths.get(path),
                StandardCharsets.UTF_8.name())) {

            //здесь мы можем использовать разделитель, например: "\\A", "\\Z" или "\\z"
            // Реализован своеобразный костыль, т.к. нет возможности использовать String.format();
            String html = scanner.useDelimiter("\\A").next().replaceAll("0%", "&&");

            return String.format(html, args).replaceAll("&&", "0%");

        } catch (IOException e) {

            logger.error("Неверно указан путь к шаблону сообщения");
            logger.error(e.fillInStackTrace().getMessage());

        }
        return null;
    }
}

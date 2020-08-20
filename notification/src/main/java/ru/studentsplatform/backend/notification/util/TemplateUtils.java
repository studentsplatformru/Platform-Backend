package ru.studentsplatform.backend.notification.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class TemplateUtils {

    private final static Logger logger = LoggerFactory.getLogger(TemplateUtils.class);

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
            logger.error(Arrays.toString(e.getStackTrace()));

        }
        return null;
    }
}

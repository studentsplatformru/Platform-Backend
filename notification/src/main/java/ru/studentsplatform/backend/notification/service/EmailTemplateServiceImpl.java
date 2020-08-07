package ru.studentsplatform.backend.notification.service;

import org.springframework.stereotype.Service;
import ru.studentsplatform.backend.notification.MessageType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService{
    @Override
    public String getEmailTemplate(MessageType type, String... args) {

        if (type.getParameterCount() != args.length)
            throw new IllegalArgumentException("Неправильное количство элементов");

        try(Scanner scanner = new Scanner(
                Paths.get(type.getPath()),
                StandardCharsets.UTF_8.name())) {

            //здесь мы можем использовать разделитель, например: "\\A", "\\Z" или "\\z"
            String html = scanner.useDelimiter("\\A").next();

            return html.replaceFirst("%s", args[0]);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

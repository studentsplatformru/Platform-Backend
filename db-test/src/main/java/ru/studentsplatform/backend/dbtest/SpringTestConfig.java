package ru.studentsplatform.backend.dbtest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;

/**
 * Содержит методы, возвращающие sql запросы для тестировангия БД.
 *
 * @author Archie-Vian (sas-artamonov@yandex.ru)
 */
public class SpringTestConfig {

    /**
     * Возращает из файла строку с запросом для наполнения БД.
     * @return строка с запросом
     */
    static String getFillingString() {
        String resource = null;
        try {
            resource = StreamUtils.copyToString(
                    new ClassPathResource("data.sql").getInputStream(), defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resource;
    }

    /**
     * Возращает из файла строку с запросом для инициализации БД.
     * @return строка с запросом
     */
    static String getInitString() {
        String resource = null;
        try {
            resource = StreamUtils.copyToString(
                    new ClassPathResource("data.sql").getInputStream(), defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resource;
    }


}

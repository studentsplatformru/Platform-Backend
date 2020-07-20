package ru.studentsplatform.backend.service.schedule.spbu_new;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

/**
 * Класс, чтобы отправлять запросы к API расписания СПБГУ.
 */
@Service
public class RequestSender {
    private static final Logger LOGGER = LogManager.getLogger(RequestSender.class);

    private final String baseUrl;
    private final ScheduleUtils scheduleUtils;

    @Autowired
    public RequestSender(@Value("${spbuBaseUrl}") String baseUrl, ScheduleUtils scheduleUtils) {
        this.baseUrl = baseUrl;
        this.scheduleUtils = scheduleUtils;
    }

    //    private static final String baseUrl = "https://timetable.spbu.ru";
    /**
     * Отправляет запрос к API расписания СПБГУ вида /api/v1/groups/{groupId}/events/{fromDate}/{toDate}.
     * Обязательно fromDate < toDate.
     *
     * @param groupId   id нужной группы в системе СПБГУ
     * @param leftDate  дата начала интервала, в котором нужно получить расписание, формат yyyy-mm-dd
     * @param rightDate дата конца интервала, в котором нужно получить расписание
     *                  (этот день не включается), формат yyyy-mm-dd
     * @return возвращает JSON респонс либо null, если не получен корректный ответ
     */
    public StringBuilder getScheduleResponse(int groupId, LocalDate leftDate, LocalDate rightDate) {
        assert (leftDate.isBefore(rightDate));
        final String fromDate = scheduleUtils.getFormattedDate(leftDate);
        final String toDate = scheduleUtils.getFormattedDate(rightDate);
        String params = String.format("/api/v1/groups/%d/events/%s/%s", groupId, fromDate, toDate);
        String fullQuery = baseUrl + params;
        LOGGER.log(Level.INFO, fullQuery);
        HttpURLConnection connection = null;
        StringBuilder result = null;
        try {
            connection = (HttpURLConnection) new URL(fullQuery).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            final int timeToWait = 700;
            connection.setConnectTimeout(timeToWait);
//            connection.setReadTimeout(700);
            connection.connect();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                result = getJson(connection);
            }
            return result;
//            TODO: решить, что делать, если не сконнектилось. Переподключаться ли ещё раз
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, e);
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * Вспомогательная функция, собирает JSON из ответа на запрос.
     *
     * @param connection текущее соединение, из которого надо забрать response
     * @return StringBuilder, в котором хранится ответ на запрос в формате JSON
     * @throws IOException от ридера
     */
    private static StringBuilder getJson(HttpURLConnection connection) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb;
    }
}

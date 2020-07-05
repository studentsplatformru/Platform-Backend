package ru.studentsplatform.backend.tlgrmbot.dataStorage;

import java.util.HashMap;
import java.util.LinkedList;

public class RunningCommand {

    /**
     * ChatId <-> Количество ожидаемых от конкретного чата сообщений с данными.
     */
    private static HashMap<Long, Integer> awaitingDataMessagesFromUserCount = new HashMap<>();
    /**
     * ChatId <-> Лист сообщений с данными, необходимыми для исполнения текущей команды для конкретного чата.
     */
    private static HashMap<Long, LinkedList<String>> dataPool = new HashMap<>();
    /**
     * ChatId <-> Обрабатываемая команда для конкретного чата.
     */
    private static HashMap<Long, String> currentCommandResolving = new HashMap<>();

    /**
     * Устанавливает команду, которая будет исполняться для текущего чата.
     */
    public static void establishRunningCommand(long chatId, String command) {
        currentCommandResolving.put(chatId,command);
    }

    /**
     * @return Возвращает значение исполняющейся команды.
     */
    public static String getRunningCommand(long chatId){
        return currentCommandResolving.get(chatId);
    }

    /**
     * Устанавливает количество ожидаемых от пользователя сообщений.
     */
    public static void setAwaitingDataAmount(long chatId, int amount) {
        awaitingDataMessagesFromUserCount.put(chatId, amount);
    }

    /**
     * Возвращает количество записей, ожидаемых ко вводу от пользователя.
     */
    private static int getAwaitingDataAmount(long chatId) {
        awaitingDataMessagesFromUserCount.putIfAbsent(chatId, 0);
        return awaitingDataMessagesFromUserCount.get(chatId);
    }

    /**
     * Вводит данные для конкретного чата.
     * @return Возвращает оставшееся количество ожидаемых сообщений.
     */
    public static boolean enterData(long chatId, String userMessage){
        dataPool.putIfAbsent(chatId, new LinkedList<>());
        dataPool.get(chatId).add(userMessage);
        System.out.println("Added data: " + userMessage);
        reduceAwaitingDataAmount(chatId);
        return isAwaitingData(chatId);
    }

    /**
     * Уменьшает количество ожидаемых ко вводу данных на 1
     * @return Возвращает оставшееся количество ожидаемых сообщений.
     */
    private static int reduceAwaitingDataAmount(long chatId){
        return awaitingDataMessagesFromUserCount.replace(chatId,
                awaitingDataMessagesFromUserCount.get(chatId)-1);
    }

    /**
     * Ожидает ли команда ввода данных?.
     */
    public static boolean isAwaitingData(long chatId){
        return getAwaitingDataAmount(chatId) != 0;
    }

    /**
     * Очищает лист данных, полученных от пользователя.
     * @return Лист, каким он был до очистки.
     */
    public static LinkedList<String> getData(long chatId){
        return new LinkedList<>(dataPool.remove(chatId));
    }
}

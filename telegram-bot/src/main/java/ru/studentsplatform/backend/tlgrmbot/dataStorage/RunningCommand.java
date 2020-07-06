package ru.studentsplatform.backend.tlgrmbot.dataStorage;

import ru.studentsplatform.backend.tlgrmbot.config.BotCommands;

import java.util.HashMap;
import java.util.LinkedList;

public class RunningCommand {
    /**
     * Обрабатываемая команда для каждого конкретного чата.
     */
    private static HashMap<Long, BotCommands> currentCommandResolving = new HashMap<>();

    /**
     * Устанавливает команду, которая будет исполняться для текущего чата.
     */
    public static boolean establishRunningCommand(long chatId, BotCommands command) {
        currentCommandResolving.put(chatId, command);
        return command.isRequiredParameters();
    }

    /**
     * Удаляет запись об исполняющейся команде.
     * @return Значение исполняющейся команды.
     */
    public static BotCommands pullRunningCommand(long chatId){
        return currentCommandResolving.remove(chatId);
    }

    /**
     * @return Показывает значение исполняющейся команды.
     */
    public static BotCommands seeRunningCommand(long chatId){
        currentCommandResolving.putIfAbsent(chatId, BotCommands.UNKNOWN);
        return currentCommandResolving.get(chatId);
    }

}

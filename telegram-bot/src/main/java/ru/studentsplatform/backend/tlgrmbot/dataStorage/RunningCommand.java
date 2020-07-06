package ru.studentsplatform.backend.tlgrmbot.dataStorage;

import ru.studentsplatform.backend.tlgrmbot.config.BotCommands;

import java.util.HashMap;

public class RunningCommand {
    /**
     * Обрабатываемая команда для каждого конкретного чата.
     */
    private static HashMap<Long, BotCommands> currentCommandResolving = new HashMap<>();

    /**
     * Устанавливает команду, которая будет исполняться для текущего чата.
     * @param chatId Id чата, для которого будет выполняться команда.
     * @param command Команда, выполнение которой будет начато для конкретного чата.
     */
    public static void establishRunningCommand(long chatId, BotCommands command) {
        currentCommandResolving.put(chatId, command);
    }

    /**
     * Удаляет запись об исполняющейся команде.
     * @param chatId Id чата, для которого выполняется команда.
     * @return Значение исполняющейся команды.
     */
    public static BotCommands pullRunningCommand(long chatId) {
        return currentCommandResolving.remove(chatId);
    }

    /**
     * @param chatId Id чата, для которого выполняется команда.
     * @return Показывает значение исполняющейся команды.
     */
    public static BotCommands seeRunningCommand(long chatId) {
        currentCommandResolving.putIfAbsent(chatId, BotCommands.UNKNOWN);
        return currentCommandResolving.get(chatId);
    }
}

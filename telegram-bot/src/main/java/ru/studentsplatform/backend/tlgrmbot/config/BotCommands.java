package ru.studentsplatform.backend.tlgrmbot.config;

import java.util.HashMap;

public enum BotCommands {
    SCHEDULE("/schedule", 4), UNKNOWN("unknown", 0);

    private String commandMessage;
    private int requiredDataNum;

    private static HashMap<String, BotCommands> commands = new HashMap<>();

    static {
        commands.put(SCHEDULE.getCommandMessage(), SCHEDULE);
        commands.put(UNKNOWN.getCommandMessage(), UNKNOWN);
    }

    BotCommands(String commandMessage, int requiredDataNum){
        this.commandMessage = commandMessage;
        this.requiredDataNum = requiredDataNum;
    }

    public String getCommandMessage() {
        return commandMessage;
    }

    public int getRequiredDataNum(){
        return requiredDataNum;
    }

    /**
     * Получаем объект команды по его полю commandMessage.
     * @param commandMessage Текст команды.
     * @return объект команды
     */
    public static BotCommands getCommandObject(String commandMessage){
        return commands.get(commandMessage);
    }

}

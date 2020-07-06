package ru.studentsplatform.backend.tlgrmbot.config;

import java.util.HashMap;

public enum BotCommands {
    SCHEDULE("/schedule", true), UNKNOWN("unknown", false);

    private String commandMessage;
    private boolean requiresDataEntry;

    private static HashMap<String, BotCommands> availableCommands = new HashMap<>();

    static {
        availableCommands.put(SCHEDULE.getCommandMessage(), SCHEDULE);
    }

    BotCommands(String commandMessage, boolean reqiresDataEntry){
        this.commandMessage = commandMessage;
        this.requiresDataEntry = reqiresDataEntry;
    }

    public String getCommandMessage() {
        return commandMessage;
    }

    public boolean isRequiredParameters(){
        return requiresDataEntry;
    }

    /**
     * Получаем объект команды по его полю commandMessage.
     * @param commandMessage Текст команды.
     * @return объект команды
     */
    public static BotCommands transformMessageToCommand(String commandMessage){
        return availableCommands.get(commandMessage);
    }



}

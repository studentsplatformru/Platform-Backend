package ru.studentsplatform.backend.tlgrmbot.config;

import java.util.HashMap;

public enum BotCommands {
    SCHEDULE("/schedule", true), UNKNOWN("unknown", false);

    private String commandMessage;
    private boolean requiresDataEntry;

    BotCommands(String commandMessage, boolean reqiresDataEntry) {
        this.commandMessage = commandMessage;
        this.requiresDataEntry = reqiresDataEntry;
    }

    public String getCommandMessage() {
        return commandMessage;
    }

    public boolean isRequiredParameters() {
        return requiresDataEntry;
    }

    /**
     * Получаем объект команды по его полю commandMessage.
     * @param commandMessage Текст команды.
     * @return объект команды
     */
    public static BotCommands getBotCommandByName(String commandMessage){
        for (BotCommands command: BotCommands.values()) {
            if(command.getCommandMessage().equals(commandMessage)){
                return command;
            }
        }
        return UNKNOWN;
    }



}

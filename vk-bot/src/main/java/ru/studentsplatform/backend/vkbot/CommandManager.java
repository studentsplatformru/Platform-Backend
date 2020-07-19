package ru.studentsplatform.backend.vkbot;

import java.util.HashSet;

/**
 * Выборка команд и переход к их методам
 */

public class CommandManager {
    private static HashSet<Command> commands = new HashSet<>();

    static {
        commands.add(new Test("test"));
        commands.add(new sendSmile("sendSmile"));
    }

    public static HashSet<Command> getCommands(){
        return commands;
    }
    public static void addCommand(Command command) { commands.add(command);}
}

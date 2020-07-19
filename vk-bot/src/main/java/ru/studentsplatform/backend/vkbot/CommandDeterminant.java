package ru.studentsplatform.backend.vkbot;

import com.vk.api.sdk.objects.messages.Message;

import java.util.Collection;

/**
 * Авторизация приложения
 *
 */

public class CommandDeterminant {

    public static Command getCommand(Collection<Command> commands, Message message) {
        String body = message.getBody();
        for (Command command : commands
        ) {
            if (command.name.equals(body.split(" ")[0])) {
                return command;
            }
        }
        return new Test("test");
    }
}

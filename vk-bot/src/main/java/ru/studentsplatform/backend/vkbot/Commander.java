package ru.studentsplatform.backend.vkbot;

import com.vk.api.sdk.objects.messages.Message;


public class Commander {

    /**
     * Обработка сообщений, получаемых через сервис Вконтакте.
     * @param message сообщение (запрос) пользователя
     */
    public static void execute(Message message){
        CommandDeterminant.getCommand(CommandManager.getCommands(), message).exec(message);
    }
}

package ru.studentsplatform.backend.vkbot;

import com.vk.api.sdk.objects.messages.Message;

/**
 * Обработка неизвестных команд
 */

public class Test extends Command {

    public Test(String name) {
        super(name);
    }

    @Override
    public void exec(Message message) {
        new VKManager().sendMessage("Неизвестная команда", message.getUserId());
    }
}

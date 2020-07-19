package ru.studentsplatform.backend.vkbot;

import com.vk.api.sdk.objects.messages.Message;

/**
 * Отправка смайлика
 */

public class sendSmile extends Command {

    public sendSmile(String name) {
        super(name);
    }

    @Override
    public void exec(Message message) {
        new VKManager().sendMessage("&#128522;\t", message.getUserId());
    }
}

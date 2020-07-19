package ru.studentsplatform.backend.vkbot;

import com.vk.api.sdk.objects.messages.Message;

/**
 * Многопоточность обработки входящих сообщений
 */

public class Messenger implements Runnable{

    private Message message;

    public Messenger(Message message){
        this.message = message;
    }

    @Override
    public void run() {
        Commander.execute(message);
    }
}
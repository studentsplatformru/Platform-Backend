package ru.studentsplatform.backend.vkbot;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Авторизация приложения
 *
 */
@Service
public class VKCore {

    private VkApiClient vk;
    private static int ts;
    private GroupActor actor;
    private static int maxMsgId = -1;
    int groupId = 197050258;
    String access_token = "f1bef71349a96a832ff4eb04a250391c59b1beebefa5216c11908de1bdc74614ef832c04202801c2de2bc";

    public VKCore() throws ClientException, ApiException {
        TransportClient transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient);

        // Загрузка конфигураций
        actor = new GroupActor(groupId, access_token);
        ts = vk.messages().getLongPollServer(actor).execute().getTs();
    }

    public GroupActor getActor() {
        return actor;
    }
    public VkApiClient getVk() {
        return vk;
    }


    public Message getMessage() throws ClientException, ApiException {

        MessagesGetLongPollHistoryQuery eventsQuery = vk.messages()
                .getLongPollHistory(actor)
                .ts(ts);
        if (maxMsgId > 0){
            eventsQuery.maxMsgId(maxMsgId);
        }
        List<Message> messages = eventsQuery
                .execute()
                .getMessages()
                .getMessages();

        if (!messages.isEmpty()){
            try {
                ts =  vk.messages()
                        .getLongPollServer(actor)
                        .execute()
                        .getTs();

            } catch (ClientException e) {
                e.printStackTrace();
            }
        }

        if (!messages.isEmpty() && !messages.get(0).isOut()) {
            /*
            messageId - максимально полученный ID, нужен, чтобы не было ошибки 10 internal server error,
            который является ограничением в API VK. В случае, если ts слишком старый (больше суток),
            а max_msg_id не передан, метод может вернуть ошибку 10 (Internal server error).
             */
            int messageId = messages.get(0).getId();
            if (messageId > maxMsgId){
                maxMsgId = messageId;
            }
            return messages.get(0);
        }
        return null;
    }
}
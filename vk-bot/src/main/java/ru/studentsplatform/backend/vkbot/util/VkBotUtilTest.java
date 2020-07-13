package ru.studentsplatform.backend.vkbot.util;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VkBotUtilTest {
    private final String HELLO_MSG;

    public VkBotUtilTest(
            @Value("${GROUP_ID}") final int GROUP_ID,
            @Value("${GROUP_ACCESS_TOKEN}") final String GROUP_ACCESS_TOKEN,
            @Value("${HELLO_MSG}") String HELLO_MSG) {
        this.HELLO_MSG = HELLO_MSG;
        start(GROUP_ID, GROUP_ACCESS_TOKEN);
    }

    /**
     * Функция запуска бота
     * @param groupId группа, к которой будет привязан бот, её айдишник смотрите в properties
     * @param groupAccessToken уникальный ключ для доступа к группе,
     *                         генерируется один раз разработчиком бота в управлении группой
     */
    private void start(int groupId, String groupAccessToken) {
        Group group = new Group(groupId, groupAccessToken);
        group.onCommand("/smile", message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text("\uD83E\uDD23")
                        .send()
        );
        group.onCommand("/photo", message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text("это мем")
                        .photoAsync("photo-120254617_457797868")
                        .send()
        );
        group.onCommand("/doc", message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text("это документ")
                        .docAsync("doc598385859_561214068")
                        .send()
        );
//        group.onTyping(userId ->
//            new Message()
//                    .from(group)
//                    .to(userId)
//                    .text("Вы начали печатать")
//                    .send()
//        );
        group.onSimpleTextMessage(message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text(HELLO_MSG)
                        .send()
        );
    }
}

package ru.studentsplatform.backend.tlgrmbot.bot.old;

import org.telegram.abilitybots.api.objects.MessageContext;

public class BotUtils {

    /**
     * Проверяет наличие в базе данных пользователя по его userId. Если пользователя там нет,
     * добавляет его туда.
     *
     * @param ctx MessageContext начального сообщения при подключении к боту
     */
    private static void checkUniversityDataBase(MessageContext ctx) {
        int userId = ctx.user().getId();

        if (!universityDataBase(userId)) {
            addTo(userId);
        }
    }

    /**
     * Проверяет информацию о пользователе в базе данных.
     *
     * @param userId уникальный идентификатор пользователя Telegram
     * @return true, если пользователь присутствует в базе данных
     */
    private static boolean universityDataBase(int userId) {
        return true;
    }

    private static void addTo(int userId) {
        //TODO здесь добавляется телеграм-userId в базу данных
    }
}

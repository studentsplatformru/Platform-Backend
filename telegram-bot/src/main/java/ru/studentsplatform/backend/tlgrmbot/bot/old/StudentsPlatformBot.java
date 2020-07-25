package ru.studentsplatform.backend.tlgrmbot.bot.old;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.*;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.studentsplatform.backend.service.entities.Respondent;
import ru.studentsplatform.backend.service.entities.Schedule.DaySchedule;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static ru.studentsplatform.backend.service.entities.enums.University.getUniversityByName;
import static ru.studentsplatform.backend.tlgrmbot.bot.Text.*;


/**
 * Бот принимает текст сообщения и отсылает его обратно пользователю.
 *//*
public class StudentsPlatformBot extends AbilityBot {

    *//**
     * Логгер.
     *//*
    private static Logger logger = LogManager.getLogger(StudentsPlatformBot.class);
    *//**
     * Уникальный токен бота.
     *//*
    private static final String BOT_TOKEN = "1334599795:AAG0yj3g1P5E4fwjpTADqoZ706OMNn-DlJQ";
    *//**
     * Уникальное имя бота.
     *//*
    private static final String BOT_USERNAME = "StudPlatformTestBot";


    protected StudentsPlatformBot(InfoTransmitter transmitter) {
        super(BOT_TOKEN, BOT_USERNAME);
    }

    *//**
     * Регистрирует и запускает инстанс бота.
     *//*
    @PostConstruct
    public void registerBot() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            logger.log(Level.FATAL, e);
        }
    }

    @Override
    public int creatorId() {
        return 64450131;
    }

    *//**
     * Событие вызывается при команде /schedule к боту.
     *
     * @return специальный объект, представляющий событие бота
     *//*
    public Ability scheduleCommand() {
        return Ability
                .builder()
                .name("schedule")
                .info(SCHEDULE_INFO.toString())
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action(this::getSchedule)
                .build();
    }

    private void getSchedule(MessageContext ctx) {
        int userId = ctx.user().getId();

        List<DaySchedule> schedules = null;
        silent.send(SCHEDULE_SUCCESS.toString(), ctx.chatId());

        if (schedules != null) {
            schedules.forEach((schedule) -> silent.send(String.valueOf(schedule), ctx.chatId()));
        } else {
            silent.send(SCHEDULE_FAILURE.toString(), ctx.chatId());
        }
    }

    *//**
     * Событие вызывается при подключении Telegram-пользователя к боту.
     *
     * @return специальный объект, представляющий событие бота
     *//*
    public Ability startCommand() {
        return Ability
                .builder()
                .name("start")
                .info(START_INFO.toString())
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action(this::toStart)
                .build();
    }

    *//**
     * Иницирует проверку пользователя в базе данных, а также отправляет стартовое сообщение.
     *
     * @param ctx MessageContext начального сообщения при подключении к боту
     *//*
    private void toStart(MessageContext ctx) {
        checkUniversityDataBase(ctx);
        silent.send("Start Message", ctx.chatId()); //TODO достать эту строку откуда-нибудь
    }

    *//**
     * Проверяет наличие в базе данных пользователя по его userId. Если пользователя там нет,
     * добавляет его туда.
     *
     * @param ctx MessageContext начального сообщения при подключении к боту
     *//*
    private void checkUniversityDataBase(MessageContext ctx) {
        int userId = ctx.user().getId();

        if (!universityDataBase(userId)) {
            addTo(userId);
        }
    }

    *//**
     * Проверяет информацию о пользователе в базе данных.
     *
     * @param userId уникальный идентификатор пользователя Telegram
     * @return true, если пользователь присутствует в базе данных
     *//*
    private boolean universityDataBase(int userId) {
        return true;
    }

    private void addTo(int userId) {
        //TODO здесь добавляется телеграм-userId в базу данных
    }

    public ReplyFlow setUniversityAndGroup() {
        Respondent respondent = new Respondent();

        Reply endAction = Reply.of(update -> addRespondent(respondent), Update::hasMessage);

        ReplyFlow chooseGroup = ReplyFlow
                .builder(db)
                .action(groupAction(respondent))
                .onlyIf(Update::hasMessage)
                .next(endAction)
                .build();

        ReplyFlow chooseDirection = ReplyFlow.builder(db)
                .action(directionAction(respondent))
                .onlyIf(wasUniversity())
                .next(chooseGroup)
                .build();

        return ReplyFlow
                .builder(db)
                .action(universityAction())
                .onlyIf(hasMessageWith("/setUniversity"))
                .next(chooseDirection)
                .build();
    }

    private void addRespondent(Respondent respondent) {

    }

    @NotNull
    private Consumer<Update> groupAction(Respondent respondent) {
        return upd -> {
            String direction = upd
                    .getMessage()
                    .getText();

            respondent.setDirection(direction);
            execute(ReplyConstructor.getGroupMessageAndKeyboard(upd));
        };
    }

    @NotNull
    private Consumer<Update> directionAction(Respondent respondent) {
        return upd -> {
            String universityName = upd
                    .getMessage()
                    .getText();

            respondent.setUniversity(getUniversityByName(universityName));
            execute(ReplyConstructor.getDirectionMessageAndKeyboard(upd));
        };
    }

    private Consumer<Update> universityAction() {
        return upd -> execute(ReplyConstructor.getUniversityMessageAndKeyboard(upd));
    }

    private Predicate<Update> hasMessageWith(String msg) {
        return upd -> upd
                .getMessage()
                .getText()
                .equalsIgnoreCase(msg);
    }

    private Predicate<Update> wasUniversity() {
        return upd -> {
            String text = upd
                    .getMessage()
                    .getText();

            return getUniversityByName(text) != null;
        };
    }

    public void execute(SendMessage message) {
        try {
            sender.execute(message);
        } catch (TelegramApiException e) {
            logger.log(Level.ERROR, e);
        }
    }
}
*/
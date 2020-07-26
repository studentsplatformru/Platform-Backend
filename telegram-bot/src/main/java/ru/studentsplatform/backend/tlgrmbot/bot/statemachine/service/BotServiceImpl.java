package ru.studentsplatform.backend.tlgrmbot.bot.statemachine.service;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.studentsplatform.backend.service.entities.enums.University;
import ru.studentsplatform.backend.tlgrmbot.bot.Text;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.event.TelegramBotEvent;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.state.TelegramBotState;

@Service
public class BotServiceImpl implements BotService {

    private static final Logger logger = LogManager.getLogger(BotServiceImpl.class);

    private final StateMachinePersister<TelegramBotState, TelegramBotEvent, String> persister;
    private final StateMachineFactory<TelegramBotState, TelegramBotEvent> stateMachineFactory;

    @SuppressWarnings("all") //игнорирует предупреждения в данном конструкторе
    public BotServiceImpl(
            StateMachinePersister<TelegramBotState, TelegramBotEvent, String> persister,
            StateMachineFactory<TelegramBotState, TelegramBotEvent> stateMachineFactory) {
        this.persister = persister;
        this.stateMachineFactory = stateMachineFactory;
    }

    @Override
    public void define(Update update, AbsSender absSender) {
        final StateMachine<TelegramBotState, TelegramBotEvent> stateMachine = stateMachineFactory.getStateMachine();
        String userId = String.valueOf(update
                .getMessage()
                .getFrom()
                .getId());

        try {
            persister.restore(stateMachine, userId);
        } catch (Exception e) {
            logger.log(Level.ERROR, e);
        }

        TelegramBotState state = stateMachine.getState().getId();

        switch (state) {
            case CHOOSING_UNIVERSITY:
                selectUniversity(update, absSender, stateMachine);
                break;
            case SPBU_SELECTED:
                selectGroupSpbu(update, absSender, stateMachine);
                break;
            case NEW:
                stateNewNotification(update, absSender);
                break;
            case END:
                stateEndNotification(update, absSender);
                break;
        }
    }

    @Override
    public void setInfo(AbsSender absSender, User user, Chat chat) {
        final StateMachine<TelegramBotState, TelegramBotEvent> stateMachine = stateMachineFactory.getStateMachine();
        stateMachine.getExtendedState().getVariables().put("CHAT", chat);
        stateMachine.getExtendedState().getVariables().put("ABS_SENDER", absSender);
        stateMachine.sendEvent(TelegramBotEvent.SET_INFO);

        try {
            persister.persist(stateMachine, String.valueOf(user.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selectUniversity(Update update,
                                  AbsSender absSender,
                                  StateMachine<TelegramBotState, TelegramBotEvent> stateMachine) {

        // final StateMachine<TelegramBotState, TelegramBotEvent> stateMachine = stateMachineFactory.getStateMachine();

        String userId = String.valueOf(update //TODO тут тоже подумать по дублированию кода
                .getMessage()
                .getFrom()
                .getId());
        stateMachine.getExtendedState().getVariables().put("UPDATE", update);
        stateMachine.getExtendedState().getVariables().put("ABS_SENDER", absSender);

        String message = update.getMessage().getText();
        University universityByName = University.getUniversityByName(message);

        switch (universityByName) { //TODO здесь по факту может быть null, как игнорировать этот варнинг?
            case SPBU:
                stateMachine.getExtendedState().getVariables().put("UNIVERSITY", universityByName);
                stateMachine.sendEvent(TelegramBotEvent.SPBU);
                break;
            default:
                wrongReplyNotification(update, absSender);
                break;
        }

        try {
            persister.persist(stateMachine, userId);
        } catch (Exception e) {
            logger.log(Level.ERROR, e);
        }
    }

    private void selectGroupSpbu(Update update,
                                 AbsSender absSender,
                                 StateMachine<TelegramBotState, TelegramBotEvent> stateMachine) {
        // final StateMachine<TelegramBotState, TelegramBotEvent> stateMachine = stateMachineFactory.getStateMachine();

        String userId = String.valueOf(update
                .getMessage()
                .getFrom()
                .getId());
        stateMachine.getExtendedState().getVariables().put("UPDATE", update);
        stateMachine.getExtendedState().getVariables().put("ABS_SENDER", absSender);

        String message = update.getMessage().getText();

        switch (message) {  //TODO здесь свич надо будет порешить
            case "19.Б03-мкн":
                stateMachine.getExtendedState().getVariables().put("GROUP", message);
                stateMachine.sendEvent(TelegramBotEvent.SPBU_GROUP);
                break;
            default:
                wrongReplyNotification(update, absSender);
                break;
        }

        try {
            persister.persist(stateMachine, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void save(Update update, AbsSender absSender) {
        final StateMachine<TelegramBotState, TelegramBotEvent> stateMachine = stateMachineFactory.getStateMachine();
        try {
            persister.restore(stateMachine, String.valueOf(update.getMessage().getChatId()));
            stateMachine.getExtendedState().getVariables().put("UPDATE", update);
            stateMachine.getExtendedState().getVariables().put("ABS_SENDER", absSender);
            stateMachine.sendEvent(TelegramBotEvent.SAVE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stateEndNotification(Update update, AbsSender absSender) {
        long chatId = update.getMessage().getChatId();
        String text = Text.STATE_END_NOTIFICATION.toString();

        sendNotification(chatId, text, absSender);
    }

    private void stateNewNotification(Update update, AbsSender absSender) {
        long chatId = update.getMessage().getChatId();
        String text = Text.STATE_NEW_NOTIFICATION.toString();

        sendNotification(chatId, text, absSender);
    }

    private void wrongReplyNotification(Update update, AbsSender absSender) {
        long chatId = update.getMessage().getChatId();
        String text = Text.WRONG_REPLY.toString();

        sendNotification(chatId, text, absSender);
    }

    private void sendNotification(long chatId, String text, AbsSender absSender) {
        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText(text);

        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            logger.log(Level.ERROR, e);
        }
    }
}

package ru.studentsplatform.backend.tlgrmbot.bot.statemachine.service;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.event.TelegramBotEvent;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.state.TelegramBotState;

@Service
public class BotServiceImpl implements BotService {
    private final StateMachinePersister<TelegramBotState, TelegramBotEvent, String> persister;
    private final StateMachineFactory<TelegramBotState, TelegramBotEvent> stateMachineFactory;
    private String userId;

    @SuppressWarnings("all") //игнорирует предупреждения в данном конструкторе
    public BotServiceImpl(
            StateMachinePersister<TelegramBotState, TelegramBotEvent, String> persister,
            StateMachineFactory<TelegramBotState, TelegramBotEvent> stateMachineFactory) {
        this.persister = persister;
        this.stateMachineFactory = stateMachineFactory;
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

    @Override
    public void selectSpbu(Update update, AbsSender absSender) {
        userId = String.valueOf(update
                .getMessage()
                .getFrom()
                .getId());

        final StateMachine<TelegramBotState, TelegramBotEvent> stateMachine = stateMachineFactory.getStateMachine();

        try {
            persister.restore(stateMachine, userId);
            stateMachine.getExtendedState().getVariables().put("UPDATE", update);
            stateMachine.getExtendedState().getVariables().put("ABS_SENDER", absSender);
            stateMachine.sendEvent(TelegramBotEvent.SPBU);
            persister.persist(stateMachine, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectGroupSpbu(Update update, AbsSender absSender) {
        userId = String.valueOf(update
                .getMessage()
                .getFrom()
                .getId());

        final StateMachine<TelegramBotState, TelegramBotEvent> stateMachine = stateMachineFactory.getStateMachine();

        try {
            persister.restore(stateMachine, userId);
            stateMachine.getExtendedState().getVariables().put("UPDATE", update);
            stateMachine.getExtendedState().getVariables().put("ABS_SENDER", absSender);
            stateMachine.sendEvent(TelegramBotEvent.SPBU_GROUP);
            persister.persist(stateMachine, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Update update, AbsSender absSender) {
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
}

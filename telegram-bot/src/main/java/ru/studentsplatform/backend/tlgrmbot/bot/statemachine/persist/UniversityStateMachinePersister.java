package ru.studentsplatform.backend.tlgrmbot.bot.statemachine.persist;

import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.event.TelegramBotEvent;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.state.TelegramBotState;

import java.util.HashMap;

public class UniversityStateMachinePersister implements StateMachinePersist<TelegramBotState, TelegramBotEvent, String> {
    private final HashMap<String, StateMachineContext<TelegramBotState, TelegramBotEvent>> contexts = new HashMap<>();

    @Override
    public void write(StateMachineContext<TelegramBotState, TelegramBotEvent> stateMachineContext, String context) {
        contexts.put(context, stateMachineContext);
    }

    @Override
    public StateMachineContext<TelegramBotState, TelegramBotEvent> read(String context) {
        return contexts.get(context);
    }
}

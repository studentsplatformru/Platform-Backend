package ru.studentsplatform.backend.tlgrmbot.bot.statemachine.actions;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.event.TelegramBotEvent;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.state.TelegramBotState;

public class ErrorAction implements Action<TelegramBotState, TelegramBotEvent> {
    @Override
    public void execute(StateContext<TelegramBotState, TelegramBotEvent> stateContext) {
        System.out.println("Вы ещё не выбрали " + stateContext.getTarget().getId().toString());
    }
}

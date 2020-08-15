package ru.studentsplatform.backend.telegrambot.statemachine.actions;

import org.springframework.statemachine.StateContext;
import ru.studentsplatform.backend.telegrambot.statemachine.event.TelegramBotEvent;
import ru.studentsplatform.backend.telegrambot.statemachine.state.TelegramBotState;

/**
 * Действие, выполняемое при неудачном переходе состояния.
 */
public class ErrorAction extends AbstractAction {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(StateContext<TelegramBotState, TelegramBotEvent> stateContext) {
		System.out.println("Вы ещё не выбрали " + stateContext.getTarget().getId().toString());
	}
}

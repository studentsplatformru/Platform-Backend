package ru.studentsplatform.backend.telegrambot.statemachine.persist;

import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import ru.studentsplatform.backend.telegrambot.statemachine.event.TelegramBotEvent;
import ru.studentsplatform.backend.telegrambot.statemachine.state.TelegramBotState;

import java.util.HashMap;

/**
 * Отвечает за сохранение и восстановление состояния конкретной машины.
 */
public class UniversityStateMachinePersister
		implements StateMachinePersist<TelegramBotState, TelegramBotEvent, String> {
	private final HashMap<String, StateMachineContext<TelegramBotState, TelegramBotEvent>> contexts = new HashMap<>();

	/**
	 * Запись состояния.
	 *
	 * @param stateMachineContext состояние машины
	 * @param context             параметр, относительно которого записывается состояние
	 */
	@Override
	public void write(StateMachineContext<TelegramBotState, TelegramBotEvent> stateMachineContext, String context) {
		contexts.put(context, stateMachineContext);
	}

	/**
	 * Восстановление состояния.
	 *
	 * @param context параметр, относительного которого восстанавливается состояние
	 */
	@Override
	public StateMachineContext<TelegramBotState, TelegramBotEvent> read(String context) {
		return contexts.get(context);
	}
}

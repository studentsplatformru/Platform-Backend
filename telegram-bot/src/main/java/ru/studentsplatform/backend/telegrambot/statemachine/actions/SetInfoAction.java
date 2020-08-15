package ru.studentsplatform.backend.telegrambot.statemachine.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.statemachine.StateContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.studentsplatform.backend.telegrambot.Text;
import ru.studentsplatform.backend.telegrambot.keyboard.KeyboardConstructor;
import ru.studentsplatform.backend.telegrambot.statemachine.event.TelegramBotEvent;
import ru.studentsplatform.backend.telegrambot.statemachine.state.TelegramBotState;

/**
 * Действие, выполняемое при событии SET_INFO. Предлагает пользователю выбрать университет
 * с помощью кастомной клавиатуры.
 */
public class SetInfoAction extends AbstractAction {

	private static final Logger LOGGER = LogManager.getLogger(SetInfoAction.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(StateContext<TelegramBotState, TelegramBotEvent> stateContext) {
		Chat chat = (Chat) stateContext.getExtendedState().getVariables().remove("Chat");
		AbsSender absSender = (AbsSender) stateContext.getExtendedState().getVariables().remove("AbsSender");

		logEvent(stateContext, LOGGER);

		SendMessage message = new SendMessage()
				.setChatId(chat.getId())
				.setText(Text.SET_INFO_MESSAGE.toString())
				.setReplyMarkup(KeyboardConstructor.getUniversitiesKeyboard());

		try {
			absSender.execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
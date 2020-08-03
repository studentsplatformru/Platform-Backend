package ru.studentsplatform.backend.tlgrmbot.bot.statemachine.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.statemachine.StateContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.studentsplatform.backend.entities.model.enums.UniversityEnum;
import ru.studentsplatform.backend.entities.model.university.University;
import ru.studentsplatform.backend.tlgrmbot.bot.Text;
import ru.studentsplatform.backend.tlgrmbot.bot.keyboard.KeyboardConstructor;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.event.TelegramBotEvent;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.state.TelegramBotState;

/**
 * Действие, выполняемое при событии SPBU. Предлагает пользователю выбрать группу
 * с помощью кастомной клавиатуры.
 */
public class SpbuAction extends AbstractAction {

	private static final Logger LOGGER = LogManager.getLogger(SpbuAction.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(StateContext<TelegramBotState, TelegramBotEvent> stateContext) {
		Update update = (Update) stateContext
				.getExtendedState()
				.getVariables()
				.remove("Update"); //TODO дублирование кода, подумать
		AbsSender absSender = (AbsSender) stateContext
				.getExtendedState()
				.getVariables()
				.remove("AbsSender");
		UniversityEnum universityEnum = (UniversityEnum) stateContext
				.getExtendedState()
				.getVariables()
				.remove("UniversityEnum");

		University university = new University();
		university.setUniversity(universityEnum);

		stateContext.getExtendedState().getVariables().put("University", university);

		logEvent(stateContext, LOGGER);

		SendMessage message = new SendMessage()
				.setChatId(update.getMessage().getChatId())
				.setText(Text.GROUP_STATE.toString())
				.setReplyMarkup(KeyboardConstructor.getGroupsKeyboard());

		try {
			absSender.execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}

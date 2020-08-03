package ru.studentsplatform.backend.tlgrmbot.bot.statemachine.actions;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.statemachine.StateContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.studentsplatform.backend.entities.model.university.University;
import ru.studentsplatform.backend.service.crud.PlaceStudyService;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.event.TelegramBotEvent;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.state.TelegramBotState;

/**
 * Действие, выполняемое при событии SPBU_GROUP. Сохраняет информацию о пользователе в базу данных.
 */
public class SpbuGroupAction extends AbstractAction {

	private static final Logger LOGGER = LogManager.getLogger(SpbuGroupAction.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(StateContext<TelegramBotState, TelegramBotEvent> stateContext) {
		Update update = (Update) stateContext
				.getExtendedState()
				.getVariables()
				.remove("Update");
		AbsSender absSender = (AbsSender) stateContext
				.getExtendedState()
				.getVariables()
				.remove("AbsSender");
		String group = (String) stateContext
				.getExtendedState()
				.getVariables()
				.remove("Group");
		University university = (University) stateContext
				.getExtendedState()
				.getVariables()
				.remove("University");
/*        PlaceStudyMapper mapper = (PlaceStudyMapper) stateContext
                .getExtendedState()
                .getVariables()
                .remove()("MAPPER");*/
		PlaceStudyService service = (PlaceStudyService) stateContext
				.getExtendedState()
				.getVariables()
				.remove("Service");

/*        Team team = new Team();
        team.setTeamName(group);

        User user = new User();

        PlaceStudy placeStudy = new PlaceStudy();
        placeStudy.setUser(user);
        placeStudy.setTeam(team);
        placeStudy.setUniversity(university);

        service.create(placeStudy);*/

		logEvent(stateContext, LOGGER);

		SendMessage message = new SendMessage()
				.setChatId(update.getMessage().getChatId())
				.setText("Окей, вы внесли свои данные")
				.setReplyMarkup(new ReplyKeyboardRemove());

		LOGGER.log(Level.INFO, "ДАННЫЕ СОХРАНЕНЫ");
		try {
			absSender.execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
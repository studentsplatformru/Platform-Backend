package ru.studentsplatform.backend.telegrambot.statemachine.service;

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
import ru.studentsplatform.backend.entities.model.enums.UniversityEnum;
import ru.studentsplatform.backend.service.crud.PlaceStudyService;
import ru.studentsplatform.backend.telegrambot.Text;
import ru.studentsplatform.backend.telegrambot.statemachine.event.TelegramBotEvent;
import ru.studentsplatform.backend.telegrambot.statemachine.state.TelegramBotState;

import java.util.Map;

/**
 * Сопоставляет методы и события машины состояний, по которым должны совершаться переходы между состояниями.
 */
@Service
public class BotServiceImpl implements BotService {

	private static final Logger LOGGER = LogManager.getLogger(BotServiceImpl.class);

	private final StateMachinePersister<TelegramBotState, TelegramBotEvent, String> persister;
	private final StateMachineFactory<TelegramBotState, TelegramBotEvent> stateMachineFactory;
	//private final PlaceStudyMapper placeStudyMapper;
	private final PlaceStudyService placeStudyService;

	/**
	 * Конструктор.
	 *
	 * @param persister           Осуществляет сохранение и восстановление состояний
	 * @param stateMachineFactory Строит объект машины состояний
	 * @param placeStudyService   для работы с базой данных
	 */
	public BotServiceImpl(StateMachinePersister<TelegramBotState, TelegramBotEvent, String> persister,
						  StateMachineFactory<TelegramBotState, TelegramBotEvent> stateMachineFactory,
						  PlaceStudyService placeStudyService) {
		this.persister = persister;
		this.stateMachineFactory = stateMachineFactory;
		this.placeStudyService = placeStudyService;
	}

	/**
	 * Определяет текущее состояние и вызывает соответствующие методы.
	 *
	 * @param update    объект, представляющий сообщение от пользователя
	 * @param absSender экземпляр бота, для отправки сообщений
	 */
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
			LOGGER.log(Level.ERROR, e);
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
			default:
				break;
		}
	}

	/**
	 * Меняет состояние машины и запускает процесс записи информации о пользователе в базу данных.
	 *
	 * @param absSender экземпляр бота, для отправки сообщений
	 * @param user      объект пользователя
	 * @param chat      объект, представляющий диалог бота и пользователя
	 */
	@Override
	public void setInfo(AbsSender absSender, User user, Chat chat) {
		final StateMachine<TelegramBotState, TelegramBotEvent> stateMachine = stateMachineFactory.getStateMachine();

		Map<Object, Object> stateMachineVariables = stateMachine
				.getExtendedState()
				.getVariables();

		stateMachineVariables.put("Chat", chat);
		stateMachineVariables.put("AbsSender", absSender);

		stateMachine.sendEvent(TelegramBotEvent.SET_INFO);

		try {
			persister.persist(stateMachine, String.valueOf(user.getId()));
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e);
		}
	}

	/**
	 * Переключает состояние пользователя и предлагает выбор университета.
	 *
	 * @param update       объект, представляющий сообщение от пользователя
	 * @param absSender    экземпляр бота, для отправки сообщений
	 * @param stateMachine текущая машина состояний пользователя
	 */
	private void selectUniversity(Update update,
								  AbsSender absSender,
								  StateMachine<TelegramBotState, TelegramBotEvent> stateMachine) {

		String userId = String.valueOf(update //TODO тут тоже подумать по дублированию кода
				.getMessage()
				.getFrom()
				.getId());

		Map<Object, Object> stateMachineVariables = stateMachine
				.getExtendedState()
				.getVariables();

		stateMachineVariables.put("Update", update);
		stateMachineVariables.put("AbsSender", absSender);

		String message = update
				.getMessage()
				.getText();
		UniversityEnum universityByName = UniversityEnum.getUniversityByName(message);

		switch (universityByName) { //TODO здесь по факту может быть null, как игнорировать этот варнинг?
			case SPBU:
				stateMachineVariables.put("University", universityByName);
				stateMachine.sendEvent(TelegramBotEvent.SPBU);
				break;
			default:
				wrongReplyNotification(update, absSender);
				break;
		}

		try {
			persister.persist(stateMachine, userId);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e);
		}
	}

	/**
	 * Переключает состояние пользователя и предлагает выбор группы университета СПБГУ.
	 *
	 * @param update       объект, представляющий сообщение от пользователя
	 * @param absSender    экземпляр бота, для отправки сообщений
	 * @param stateMachine текущая машина состояний пользователя
	 */
	private void selectGroupSpbu(Update update,
								 AbsSender absSender,
								 StateMachine<TelegramBotState, TelegramBotEvent> stateMachine) {

		String userId = String.valueOf(update
				.getMessage()
				.getFrom()
				.getId());

		Map<Object, Object> stateMachineVariables = stateMachine
				.getExtendedState()
				.getVariables();

		stateMachineVariables.put("Update", update);
		stateMachineVariables.put("AbsSender", absSender);

		String message = update
				.getMessage()
				.getText();

		switch (message) {  //TODO здесь надо будет с группами определиться
			case "19.Б03-мкн":
				stateMachineVariables.put("Group", message);
				stateMachineVariables.put("Service", placeStudyService);
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

	/**
	 * Уведомление, которое присылает бот при состоянии End.
	 *
	 * @param update    объект, представляющий сообщение от пользователя
	 * @param absSender экземпляр бота, для отправки сообщений
	 */
	private void stateEndNotification(Update update, AbsSender absSender) {
		long chatId = update.getMessage().getChatId();
		String text = Text.STATE_END_NOTIFICATION.toString();

		sendNotification(chatId, text, absSender);
	}

	/**
	 * Уведомление, которое присылает бот при состоянии New.
	 *
	 * @param update    объект, представляющий сообщение от пользователя
	 * @param absSender экземпляр бота, для отправки сообщений
	 */
	private void stateNewNotification(Update update, AbsSender absSender) {
		long chatId = update.getMessage().getChatId();
		String text = Text.STATE_NEW_NOTIFICATION.toString();

		sendNotification(chatId, text, absSender);
	}

	/**
	 * Уведомление, которое присылает бот при неправильном ответе на запрос бота.
	 *
	 * @param update    объект, представляющий сообщение от пользователя
	 * @param absSender экземпляр бота, для отправки сообщений
	 */
	private void wrongReplyNotification(Update update, AbsSender absSender) {
		long chatId = update.getMessage().getChatId();
		String text = Text.WRONG_REPLY.toString();

		sendNotification(chatId, text, absSender);
	}

	/**
	 * Отправляет переданное сообщение.
	 *
	 * @param chatId    место, куда надо отправить уведомление
	 * @param text      текст уведомления
	 * @param absSender экземпляр бота, для отправки сообщений
	 */
	private void sendNotification(long chatId, String text, AbsSender absSender) {
		SendMessage message = new SendMessage()
				.setChatId(chatId)
				.setText(text);

		try {
			absSender.execute(message);
		} catch (TelegramApiException e) {
			LOGGER.log(Level.ERROR, e);
		}
	}
}

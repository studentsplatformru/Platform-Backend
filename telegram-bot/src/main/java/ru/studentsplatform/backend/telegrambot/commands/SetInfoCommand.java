package ru.studentsplatform.backend.telegrambot.commands;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.studentsplatform.backend.telegrambot.statemachine.service.BotService;
import ru.studentsplatform.backend.telegrambot.Text;

/**
 * Комманда /set_info позволяет внести в базу данных информацию о пользователе Telegram.
 */
@Service
public class SetInfoCommand extends BotCommand {

	private final BotService botService;

	/**
	 * Конструктор. Присваивает имя и описание команды.
	 *
	 * @param botService позволяет изменить состояние пользователя с помощью
	 *                   {@link org.springframework.statemachine.StateMachine}.
	 */
	public SetInfoCommand(BotService botService) {
		super("set_info", Text.SET_INFO_DESCRIPTION.toString());
		this.botService = botService;
	}

	/**
	 * Выполняет последовательность действий для записи информации о пользователе.
	 *
	 * @param absSender объект Telegram-bot для выполнения действия
	 * @param user      объект пользователя Telegram
	 * @param chat      объект диалога пользователя с ботом
	 * @param arguments аргументы, передаваемые с командой (в данном случае не влияет на логику)
	 */
	public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
		botService.setInfo(absSender, user, chat);
	}
}
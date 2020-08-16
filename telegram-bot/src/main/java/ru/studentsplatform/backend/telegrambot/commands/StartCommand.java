package ru.studentsplatform.backend.telegrambot.commands;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.studentsplatform.backend.telegrambot.Text;

/**
 * Команда приветствия при начале работы с ботом.
 */
@Service
public class StartCommand extends BotCommand {

	private static final Logger LOGGER = LogManager.getLogger(StartCommand.class);

	/**
	 * Конструктор. Присваивает имя и описание команды.
	 */
	public StartCommand() {
		super("start", Text.START_DESCRIPTION.toString());
	}

	/**
	 * Приветствует пользователя.
	 *
	 * @param absSender объект Telegram-bot для выполнения действия
	 * @param user      объект пользователя Telegram
	 * @param chat      объект диалога пользователя с ботом
	 * @param arguments аргументы, передаваемые с командой (в данном случае не влияет на логику)
	 */
	public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
		String greetingsTitle = String.format("Привет, %s!\n", user.getFirstName());

		SendMessage startInfo = new SendMessage();
		startInfo.setChatId(chat.getId().toString());
		startInfo.setText(greetingsTitle + Text.START_MESSAGE.toString());

		printMessage(absSender, startInfo);
	}

	/**
	 * Отправляет сообщение пользователю в чат.
	 *
	 * @param absSender объект Telegram-bot для выполнения действия
	 * @param answer    объект сообщения
	 */
	private void printMessage(AbsSender absSender, SendMessage answer) {
		try {
			absSender.execute(answer);
		} catch (TelegramApiException e) {
			LOGGER.log(Level.ERROR, e);
		}
	}
}
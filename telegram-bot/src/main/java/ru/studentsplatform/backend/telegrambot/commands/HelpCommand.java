package ru.studentsplatform.backend.telegrambot.commands;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.ICommandRegistry;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.studentsplatform.backend.telegrambot.Text;

/**
 * This command helps the user to find the command they need.
 *
 * @author Timo Schulz (Mit0x2)
 */
public class HelpCommand extends BotCommand {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(HelpCommand.class);
    private final ICommandRegistry commandRegistry;

    /**
     * Конструктор. Присваивает имя и описание команды.
     *
     * @param commandRegistry Объект бота, для отображения всех зарегистрированных команд.
     */
    public HelpCommand(ICommandRegistry commandRegistry) {
        super("help", Text.HELP_DESCRIPTION.toString());
        this.commandRegistry = commandRegistry;
    }

    /**
     * Предоставляет пользователю список зарегестрированных комманд бота.
     *
     * @param absSender объект Telegram-bot для выполнения действия
     * @param user      объект пользователя Telegram
     * @param chat      объект диалога пользователя с ботом
     * @param arguments аргументы, передаваемые с командой (в данном случае не влияет на логику)
     */
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {

        StringBuilder helpMessageBuilder = new StringBuilder("<b>Help</b>\n");
        helpMessageBuilder.append("These are the registered commands for this Bot:\n\n");

        for (IBotCommand botCommand : commandRegistry.getRegisteredCommands()) {
            helpMessageBuilder.append(botCommand.toString()).append("\n\n");
        }

        SendMessage helpMessage = new SendMessage();
        helpMessage.setChatId(chat.getId().toString());
        helpMessage.enableHtml(true);
        helpMessage.setText(helpMessageBuilder.toString());

        printMessage(absSender, helpMessage);
    }

    /**
     * Отправляет сообщение пользователю в чат.
     *
     * @param absSender   объект Telegram-bot для выполнения действия
     * @param helpMessage объект сообщения
     */
    private void printMessage(AbsSender absSender, SendMessage helpMessage) {
        try {
            absSender.execute(helpMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

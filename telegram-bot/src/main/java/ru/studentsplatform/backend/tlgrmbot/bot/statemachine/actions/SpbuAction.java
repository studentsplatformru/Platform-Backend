package ru.studentsplatform.backend.tlgrmbot.bot.statemachine.actions;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.studentsplatform.backend.tlgrmbot.bot.KeyboardConstructor;
import ru.studentsplatform.backend.tlgrmbot.bot.Text;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.event.TelegramBotEvent;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.state.TelegramBotState;

public class SpbuAction implements Action<TelegramBotState, TelegramBotEvent> {
    @Override
    public void execute(StateContext<TelegramBotState, TelegramBotEvent> stateContext) {
        Update update = (Update) stateContext.getExtendedState().getVariables().get("UPDATE");
        AbsSender absSender = (AbsSender) stateContext.getExtendedState().getVariables().get("ABS_SENDER");

        System.out.println("Это ивент " + stateContext.getEvent());
        System.out.println("Это состояние из " + stateContext.getSource().getId().name());
        System.out.println("Это состояние в " + stateContext.getTarget().getId().name());

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

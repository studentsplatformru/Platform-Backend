package ru.studentsplatform.backend.tlgrmbot.bot.statemachine.actions;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.studentsplatform.backend.service.entities.Respondent;
import ru.studentsplatform.backend.service.entities.enums.University;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.event.TelegramBotEvent;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.state.TelegramBotState;

public class SpbuGroupAction implements Action<TelegramBotState, TelegramBotEvent> {
    @Override
    public void execute(StateContext<TelegramBotState, TelegramBotEvent> stateContext) {
        Update update = (Update) stateContext.getExtendedState().getVariables().get("UPDATE");
        AbsSender absSender = (AbsSender) stateContext.getExtendedState().getVariables().get("ABS_SENDER");

        System.out.println("Это ивент " + stateContext.getEvent());
        System.out.println("Это состояние из " + stateContext.getSource().getId().name());
        System.out.println("Это состояние в " + stateContext.getTarget().getId().name());

        Respondent respondent = new Respondent();
        respondent.setUniversity(University.SPBU);
        respondent.setGroupName("19.Б03-мкн");

        System.out.println("Respondent сохранён");

        SendMessage message = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText("Окей, вы успешно выбрали группу");
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

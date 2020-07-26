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
        String group = (String) stateContext.getExtendedState().getVariables().get("GROUP");
        University university = (University) stateContext.getExtendedState().getVariables().get("UNIVERSITY");

        System.out.println("Это ивент " + stateContext.getEvent());
        System.out.println("Это состояние из " + stateContext.getSource().getId().name());
        System.out.println("Это состояние в " + stateContext.getTarget().getId().name());

        Respondent respondent = new Respondent();
        respondent.setUniversity(university);
        respondent.setGroupName(group);

        System.out.println("Respondent сохранён\n" + respondent.getUniversity() + " " + respondent.getGroupName());

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

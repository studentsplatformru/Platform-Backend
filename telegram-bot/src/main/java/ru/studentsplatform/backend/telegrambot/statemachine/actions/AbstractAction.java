package ru.studentsplatform.backend.telegrambot.statemachine.actions;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import ru.studentsplatform.backend.telegrambot.statemachine.event.TelegramBotEvent;
import ru.studentsplatform.backend.telegrambot.statemachine.state.TelegramBotState;

/**
 * Действие, выполняемое при смене состояния.
 */
abstract class AbstractAction implements Action<TelegramBotState, TelegramBotEvent> {

    /**
     * Отображает информацию о смене состояния.
     *
     * @param stateContext контекст состояния
     * @param logger       логгер класса
     */
    void logEvent(StateContext<TelegramBotState, TelegramBotEvent> stateContext, Logger logger) {
        logger.log(Level.INFO, "Это ивент " + stateContext.getEvent());
        logger.log(Level.INFO, "Это состояние из " + stateContext.getSource().getId().name());
        logger.log(Level.INFO, "Это состояние в " + stateContext.getTarget().getId().name());
    }
}

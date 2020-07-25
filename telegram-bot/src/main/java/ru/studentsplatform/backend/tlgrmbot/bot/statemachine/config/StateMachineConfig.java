package ru.studentsplatform.backend.tlgrmbot.bot.statemachine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.actions.*;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.event.TelegramBotEvent;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.listener.StateMachineApplicationListener;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.persist.UniversityStateMachinePersister;
import ru.studentsplatform.backend.tlgrmbot.bot.statemachine.state.TelegramBotState;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<TelegramBotState, TelegramBotEvent> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<TelegramBotState, TelegramBotEvent> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true);
    }

    @Override
    public void configure(StateMachineStateConfigurer<TelegramBotState, TelegramBotEvent> states) throws Exception {
        states
                .withStates()
                .initial(TelegramBotState.NEW)
                .end(TelegramBotState.END)
                .states(EnumSet.allOf(TelegramBotState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<TelegramBotState,
            TelegramBotEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(TelegramBotState.NEW)
                .target(TelegramBotState.CHOOSING_UNIVERSITY)
                .event(TelegramBotEvent.SET_INFO)
                .action(setInfoAction(), errorAction())

                .and()
                .withExternal()
                .source(TelegramBotState.CHOOSING_UNIVERSITY)
                .target(TelegramBotState.SPBU_SELECTED)
                .event(TelegramBotEvent.SPBU)
                .action(spbuAction(), errorAction())

                .and()
                .withExternal()
                .source(TelegramBotState.SPBU_SELECTED)
                .target(TelegramBotState.SPBU_GROUP_SELECTED)
                .event(TelegramBotEvent.SPBU_GROUP)
                .action(spbuGroupAction(), errorAction())

                .and()
                .withExternal()
                .target(TelegramBotState.END)
                .event(TelegramBotEvent.SAVE)
                .action(saveAction(), errorAction());
    }

    @Bean
    public Action<TelegramBotState, TelegramBotEvent> setInfoAction() {
        return new SetInfoAction();
    }

    @Bean
    public Action<TelegramBotState, TelegramBotEvent> spbuAction() {
        return new SpbuAction();
    }

    @Bean
    public Action<TelegramBotState, TelegramBotEvent> errorAction() {
        return new ErrorAction();
    }

    @Bean
    public Action<TelegramBotState, TelegramBotEvent> saveAction() {
        return new SaveAction();
    }

    @Bean
    public Action<TelegramBotState, TelegramBotEvent> spbuGroupAction() {
        return new SpbuGroupAction();
    }

    @Bean
    public StateMachinePersister<TelegramBotState, TelegramBotEvent, String> persister() {
        return new DefaultStateMachinePersister<>(new UniversityStateMachinePersister());
    }
}

package ru.studentsplatform.backend.tlgrmbot.bot.statemachine.listener;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

public class StateMachineApplicationListener implements StateMachineListener {
    @Override
    public void stateChanged(State state, State state1) {

    }

    @Override
    public void stateEntered(State state) {

    }

    @Override
    public void stateExited(State state) {

    }

    @Override
    public void eventNotAccepted(Message message) {

    }

    @Override
    public void transition(Transition transition) {

    }

    @Override
    public void transitionStarted(Transition transition) {

    }

    @Override
    public void transitionEnded(Transition transition) {

    }

    @Override
    public void stateMachineStarted(StateMachine stateMachine) {

    }

    @Override
    public void stateMachineStopped(StateMachine stateMachine) {

    }

    @Override
    public void stateMachineError(StateMachine stateMachine, Exception e) {

    }

    @Override
    public void extendedStateChanged(Object o, Object o1) {

    }

    @Override
    public void stateContext(StateContext stateContext) {

    }
}

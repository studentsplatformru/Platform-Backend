package ru.studentsplatform.backend.tlgrmbot.bot.statemachine.state;

public enum TelegramBotState {
    NEW(null),
    CHOOSING_UNIVERSITY(null),
    SPBU_SELECTED("университет"),
    SPBU_GROUP_SELECTED("группу"),
    END(null);

    private final String request;

    TelegramBotState(String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return request;
    }
}

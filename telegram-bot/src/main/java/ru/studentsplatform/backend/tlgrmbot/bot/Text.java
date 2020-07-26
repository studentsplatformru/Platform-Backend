package ru.studentsplatform.backend.tlgrmbot.bot;

public enum Text {
    STATE_NEW_NOTIFICATION("Возможно, вам необходимо начать с команды /setInfo"),
    STATE_END_NOTIFICATION("Вы можете узнать доступные команды с помощью /help"),
    WRONG_REPLY("Попробуйте ввести ещё раз"),

    START_DESCRIPTION("Приветственное сообщение и начало работы с ботом."),
    START_MESSAGE("Start Message"), //TODO дополнить

    SCHEDULE_DESCRIPTION("Получить расписание за текущую неделю."),
    SCHEDULE_SUCCESS("Вот это расписание!"),
    SCHEDULE_FAILURE("Что-то пошло не так. Возможно, неправильно указаны данные."),

    HELP_DESCRIPTION("Получить список всех команд, доступных боту."),

    UNIVERSITY_STATE("Выберите университет:"),
    DIRECTION_STATE("Выберите направление:"),
    GROUP_STATE("Выберите группу:"),

    SET_INFO_DESCRIPTION("Заполнить необходимую информацию о себе для работы с расписанием."),
    SET_INFO_MESSAGE("Введите информацию о себе. ");

    private final String value;

    Text(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
